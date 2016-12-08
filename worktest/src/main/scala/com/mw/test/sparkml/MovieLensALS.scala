package com.mw.test.sparkml

import java.io.File

import com.mw.test.common.SparkUtils.SparkUtils
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.apache.spark.rdd.RDD

import scala.io.Source

/**
  * Created by wei.ma on 2016/11/2.
  * 由于该程序中最终推荐给用户十部电影，这需要用户提供对样本电影数据的评分，然后根据生成的最佳模型获取当前用户推荐电影
  * 评分数据说明:格式为UserID::MovieID::Rating::Timestamp，分为为用户编号：：电影编号：：评分：：评分时间戳
  * l用户编号范围1~6040
  * l电影编号1~3952
  * l电影评分为五星评分，范围0~5
  * l评分时间戳单位秒
  * l每个用户至少有20个电影评分
  * 用户信息格式为UserID::Age::Gender::Occupation::Zip-code，分为为用户编号：：性别：：年龄：：职业::邮编，其中各个字段说明如下：
  * l用户编号范围1~6040
  * l性别，其中M为男性，F为女性
  * l不同的数字代表不同的年龄范围，如：25代表25~34岁范围
  * l职业信息，在测试数据中提供了21中职业分类
  * l地区邮编
  * 电影数据分为三个字段，格式为MovieID::Title::Genres，分为为电影编号：：电影名：：电影类别，其中各个字段说明如下：
  * l电影编号1~3952
  * l由IMDB提供电影名称，其中包括电影上映年份
  * l电影分类，这里使用实际分类名非编号，如：Action、Crime等
  */
object MovieLensALS {
  val userRatingPath = "E:\\mojiworkspace\\MyWorkTest\\worktest\\src\\main\\resources\\source\\moverec\\ratings.dat"
  val moviesPath = "E:\\mojiworkspace\\MyWorkTest\\worktest\\src\\main\\resources\\source\\moverec\\movies.dat"

  def main(args: Array[String]) {
    // 屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    // 设置运行环境
    val sc = SparkUtils.sparkEntrance("MovieLensALS", "local[4]", null)
    // 装载用户评分，该评分由评分器生成
    val myRatings = loadRatings(userRatingPath)
    val myRatingsRDD = sc.parallelize(myRatings, 1)

    // 样本数据目录
    //    val movieLensHomeDir = args(0)
    // 装载样本评分数据，其中最后一列Timestamp取除10的余数作为key，Rating为值,即(Int,Rating)
    val ratings = sc.textFile(userRatingPath).map { line =>
      val fields = line.split("::")
      (fields(3).toLong % 10, Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble))
    }
//    ratings.collect.foreach(println)
    // 装载电影目录对照表（电影ID->电影标题）
    val movies = sc.textFile(moviesPath).map { line =>
      val fields = line.split("::")
      (fields(0).toInt, fields(1))
    }.collect().toMap
//    movies.foreach(println)

    val numRatings = ratings.count()
    val numUsers = ratings.map(_._2.user).distinct().count()
    val numMovies = ratings.map(_._2.product).distinct().count()
    println("Got " + numRatings + " ratings from " + numUsers + " users on " + numMovies + " movies.")

    // 将样本评分表以key值切分成3个部分，分别用于训练 (60%，并加入用户评分), 校验 (20%), and 测试 (20%)
    // 该数据在计算过程中要多次应用到，所以cache到内存
    val numPartitions = 4
    val training = ratings.filter(x => x._1 < 6)
      .values
      .union(myRatingsRDD) //注意ratings是(Int,Rating)，取value即可
      .repartition(numPartitions)
      .cache()

    val validation = ratings.filter(x => x._1 >= 6 && x._1 < 8)
      .values
      .repartition(numPartitions)
      .cache()

    val test = ratings.filter(x => x._1 >= 8).values.cache()

    val numTraining = training.count()
    val numValidation = validation.count()
    val numTest = test.count()

    println("Training: " + numTraining + ", validation: " + numValidation + ", test: " + numTest)

    // 训练不同参数下的模型，并在校验集中验证，获取最佳参数下的模型
    val ranks = List(8, 12)
    val lambdas = List(0.1, 10.0)
    val numIters = List(10, 20)
    var bestModel: Option[MatrixFactorizationModel] = None
    var bestValidationRmse = Double.MaxValue
    var bestRank = 0
    var bestLambda = -1.0
    var bestNumIter = -1

    for (rank <- ranks; lambda <- lambdas; numIter <- numIters) {
      val model = ALS.train(training, rank, numIter, lambda)
      val validationRmse = computeRmse(model, validation, numValidation)
      println("RMSE (validation) = " + validationRmse + " for the model trained with rank = "
        + rank + ", lambda = " + lambda + ", and numIter = " + numIter + ".")
      if (validationRmse < bestValidationRmse) {
        bestModel = Some(model)
        bestValidationRmse = validationRmse
        bestRank = rank
        bestLambda = lambda
        bestNumIter = numIter
      }
    }
    // 用最佳模型预测测试集的评分，并计算和实际评分之间的均方根误差
    val testRmse = computeRmse(bestModel.get, test, numTest)

    println("The best model was trained with rank = " + bestRank + " and lambda = " + bestLambda + ", and numIter = " + bestNumIter + ", and its RMSE on the test set is " + testRmse + ".")

    // create a naive baseline and compare it with the best model
    val meanRating = training.union(validation).map(_.rating).mean
    val baselineRmse = math.sqrt(test.map(x => (meanRating - x.rating) * (meanRating - x.rating)).mean)
    val improvement = (baselineRmse - testRmse) / baselineRmse * 100
    println("The best model improves the baseline by " + "%1.2f".format(improvement) + "%.")

    // 推荐前十部最感兴趣的电影，注意要剔除用户已经评分的电影
    val myRatedMovieIds = myRatings.map(_.product).toSet
    val candidates = sc.parallelize(movies.keys.filter(!myRatedMovieIds.contains(_)).toSeq)
    val recommendations = bestModel.get.predict(candidates.map((0, _)))
      .collect()
      .sortBy(-_.rating)
      .take(10)

    var i = 1
    println("Movies recommended for you:")
    recommendations.foreach { r =>
      println("%2d".format(i) + ": " + movies(r.product))
      i += 1
    }
    sc.stop()
  }

  /** 校验集预测数据和实际数据之间的均方根误差 **/
  def computeRmse(model: MatrixFactorizationModel, data: RDD[Rating], n: Long): Double = {
    val predictions: RDD[Rating] = model.predict(data.map(x => (x.user, x.product)))
    val predictionsAndRatings = predictions.map(x => ((x.user, x.product), x.rating))
      .join(data.map(x => ((x.user, x.product), x.rating)))
      .values
    math.sqrt(predictionsAndRatings.map(x => (x._1 - x._2) * (x._1 - x._2)).reduce(_ + _) / n)
  }

  /** 装载用户评分文件 **/
  def loadRatings(path: String): Seq[Rating] = {
    val lines = Source.fromFile(path).getLines()
    val ratings = lines.map { line =>
      val fields = line.split("::")
      Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble)
    }.filter(_.rating > 0.0)
    if (ratings.isEmpty) {
      sys.error("No ratings provided.")
    } else {
      ratings.toSeq
    }
  }
}

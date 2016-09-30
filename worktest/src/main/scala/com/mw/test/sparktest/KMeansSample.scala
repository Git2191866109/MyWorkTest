package com.mw.test.sparktest

import com.mw.test.common.SparkUtils.SparkUtils
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors

/**
  * Created by wei.ma on 2016/9/28.
  * 测试数据分：数据分为3个维度
  * 要通过kmeans算法对数据进行分类
  */
object KMeansSample {
  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
  Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
  val path = "E:\\mojiworkspace\\MyWorkTest\\worktest\\src\\main\\resources\\source\\julei"

  def main(args: Array[String]) {
    val sc = SparkUtils.sparkEntrance("KMeansSample", "local", null)

    /*加载数据*/
    val fileData = sc.textFile(path, 1)
    val parseData = fileData.map(record => Vectors.dense(record.split(" ").map(_.toDouble)))

    /*模型训练*/
    val dataModelNumber = 2 //K-Means需要预先设置有多少个簇类（K值）
    val dataModelTrainTimes = 20
    val model = KMeans.train(parseData, dataModelNumber, dataModelTrainTimes)

    /*数据模型的中心点*/
    println("Cluster centers:")
    for (c <- model.clusterCenters) {
      println("  " + c.toString)
    }
    /*使用模型测试单点数据*/
    println("Vectors 0.2 0.2 0.2 is belongs to clusters:" + model.predict(Vectors.dense("0.2 0.2 0.2".split(' ').map(_.toDouble))))
    println("Vectors 0.25 0.25 0.25 is belongs to clusters:" + model.predict(Vectors.dense("0.25 0.25 0.25".split(' ').map(_.toDouble))))
    println("Vectors 8 8 8 is belongs to clusters:" + model.predict(Vectors.dense("8 8 8".split(' ').map(_.toDouble))))

    //交叉评估1，只返回结果
    val testdata = fileData.map(s => Vectors.dense(s.split(' ').map(_.toDouble)))
    val result1 = model.predict(testdata)
    println(result1.collect.toBuffer.toString())

    //交叉评估2，返回数据集和结果
    val result2 = fileData.map {
      line =>
        val linevectore = Vectors.dense(line.split(' ').map(_.toDouble))
        val prediction = model.predict(linevectore)
        line + " " + prediction
    }
    println(result2.collect.toBuffer.toString())

    sc.stop()
  }
}

package com.moji.work.user_profile

import java.util.Properties

import com.moji.work.user_profile.common.DateUitls
import com.moji.work.user_profile.common.SparkUtils
import com.moji.work.user_profile.common.{DateUitls, SparkUtils}
import com.mw.test.common.SparkUtils.SparkUtils
import com.mw.test.scalatest.DateUitls
import org.apache.spark
import org.apache.spark.sql.types.{DataTypes, StructField}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkContext, SparkException}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by wei.ma on 2016/9/8.
  */
class User_profile {
  /**
    * 返回上一周的时间
    */
  val startTime = DateUitls.getLastWeekStart()
  val endTime = DateUitls.getLastWeekEnd()
  startTime
  endTime

  /**
    * hive-sql
    */
  val use_moji = "use moji"
  val tag_hql = "select uid tag_uid,count(distinct tagid) tag_num from tag_collection where platform='Android' and date between '" + startTime + "' and '" + endTime + "' group by uid"
  val days_hql = "select uid,count(distinct date) sum_date from commonlog_partly " +
    "where key = 'FEED_PAGE_CLICK' " +
    "and  date between '" + startTime + "' and '" + endTime + "' " +
    "and value=0 " +
    "and uid!=0 group by uid"
  val article_hql = "SELECT res.uid res_uid, count(1) AS num_uidcat FROM (SELECT a.uid, a.feed_id, b.category_id FROM feed a INNER JOIN feedid_category b ON a.feed_id = b.feed_id AND " +
    "a.url = 'url:/card/detail' AND a.date between '" + startTime + "' and '" + endTime + "') res GROUP BY res.uid"

  /**
    * 初始化spark运行环境
    */
  def init(): SparkContext = {
    val settings = new mutable.HashMap[String, String]()
    settings.put("spark.driver.cores", "3")
    settings.put("spark.executor.memory", "14g")
    settings.put("spark.driver.memory", "6g")
    settings.put("spark.executor.instances", "16")
    settings.put("spark.executor.cores", "3")
    settings.put("spark.driver.maxResultSize", "4g")
    settings.put("spark.akka.frameSize", "100")
    settings.put("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    //    val sc = SparkUtils.sparkEntrance("FEED_USER_PROFILE", "yarn-client", settings)
    val sc = SparkUtils.sparkEntrance("FEED_USER_PROFILE", "local", settings)
    sc
  }

  /**
    * 停止spark
    */
  def stopSpark() = {
    SparkUtils.sparkStoped(init())
  }

  //  host='ec2-54-223-197-216.cn-north-1.compute.amazonaws.com.cn',user='moji',passwd='BigDataMoji',db='tblu',port=3306,charset="utf8")
}

object User_profile extends spark.Logging {
  val user_profile = new User_profile
  val tag_file_path = "E:\\mojiworkspace\\moji-work\\src\\main\\resources\\data\\user_profile\\taguser"
  val day_file_path = "E:\\mojiworkspace\\moji-work\\src\\main\\resources\\data\\user_profile\\dayuser"
  val article_file_path = "E:\\mojiworkspace\\moji-work\\src\\main\\resources\\data\\user_profile\\articeluser"

  def main(args: Array[String]) {
    val sc = user_profile.init()
    try {
      //      val sqlContext = new HiveContext(sc)
      val sqlCtx = new SQLContext(sc)

      //      sqlContext.sql(user_profile.use_moji)
      //      val tagDF = sqlContext.sql(user_profile.tag_hql)
      import sqlCtx.implicits._
      val tagDF = sc.textFile(tag_file_path).map(_.split("\t")).map(tag => T_TAG(tag(0), tag(1).toInt)).toDF()
      tagDF.registerTempTable("t_tag")
      val daysDF = sc.textFile(day_file_path).map(_.split("\t")).map(day => T_DAYS(day(0), day(1).toInt)).toDF()
      daysDF.registerTempTable("t_days")
      val articleDF = sc.textFile(article_file_path).map(_.split("\t")).map(article => T_ARTICLE(article(0), article(1).toInt)).toDF()
      articleDF.registerTempTable("t_article")
      //      val daysDF = sqlContext.sql(user_profile.days_hql)
      //      daysDF.registerTempTable("t_days")
      //      val articleDF = sqlContext.sql(user_profile.article_hql)
      //      articleDF.registerTempTable("t_article")


      tagDF.persist(StorageLevel.MEMORY_ONLY_SER)
      daysDF.persist(StorageLevel.MEMORY_ONLY_SER)
      articleDF.persist(StorageLevel.MEMORY_ONLY_SER)

      val pre_data = tagDF.join(daysDF, tagDF("tag_uid").equalTo(daysDF("days_uid"))).join(articleDF, tagDF("tag_uid").equalTo(articleDF("art_uid")))

      val pre_result_row = pre_data.map(row => Row(row(0), row(1), row(3), row(5)))

      //      pre_result_row.foreach(row => print(row(0) + "..." + row(1) + "..." + row(3) + "..." + row(5)))
      //      pre_result_row.foreach(row => print(row.toString()))


      //创建JavaRDD<Row>的元数据信息
      val structFields = ArrayBuffer[StructField]()
      structFields.+=(DataTypes.createStructField("uid", DataTypes.StringType, true))
      structFields.+=(DataTypes.createStructField("tag_num", DataTypes.IntegerType, true))
      structFields.+=(DataTypes.createStructField("date_num", DataTypes.IntegerType, true))
      structFields.+=(DataTypes.createStructField("art_num", DataTypes.IntegerType, true))
      val structType = DataTypes.createStructType(structFields.toArray)

      val restDF = sqlCtx.createDataFrame(pre_result_row, structType)
      restDF.registerTempTable("result_result")

      //      restDF.foreach(row => println("result_result..." + row(0) + "..." + row(1) + "..." + row(2) + "..." + row(3)))
      //创建Properties存储数据库相关属性
      val prop = new Properties()
      prop.put("user", "root")
      prop.put("password", "123456")

      /**
        * 将数据追加到数据库
        * 注意这里的restDF的字段要和数据库建表字段一致
        */
      restDF.write.mode("append").jdbc("jdbc:mysql://127.0.0.1:3306/mojitest", "user_select", prop)

    } catch {
      case e: Exception => {
        logError("Spark执行失败.log....", e)
        throw new SparkException("Spark执行失败.....", e)
      }
    } finally {
      SparkUtils.sparkStoped(sc)
    }
  }
}

/**
  * 创建模型对象
  */
/*用户标签*/
case class T_TAG(tag_uid: String, tag_num: Int)

/*用户的活跃天数*/
case class T_DAYS(days_uid: String, date_num: Int)

/*用户文章数量的多少*/
case class T_ARTICLE(art_uid: String, art_num: Int)


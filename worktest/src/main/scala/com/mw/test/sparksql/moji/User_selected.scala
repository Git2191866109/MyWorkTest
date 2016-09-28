package com.mw.test.sparksql.moji

import com.mw.test.common.SparkUtils.SparkUtils
import com.mw.test.scalatest.DateUitls
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.Row
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.types.{DataTypes, StructField}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkContext, SparkException}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by wei.ma on 2016/9/23.
  */

class User_selected {
  /**
    * 返回上一周的时间
    */
  val startTime = DateUitls.getLastWeekStart
  val endTime = DateUitls.getLastWeekEnd
  val today = DateUitls.getToday
  startTime
  endTime
  today

  /**
    * hive-sql
    */
  val use_moji = "use moji"
  val tag_hql = "select uid tag_uid,count(distinct tagid) tag_num from tag_collection " +
    "where platform='Android' " +
    "and date between '" + startTime + "' and '" + endTime + "' " +
    "group by uid"
  val days_hql = "select uid days_uid,count(distinct date) date_num from commonlog_partly " +
    "where key = 'FEED_PAGE_CLICK' " +
    "and  date between '" + startTime + "' and '" + endTime + "' " +
    "and value=0 " +
    "and uid!=0 " +
    "group by uid"
  val article_hql = "SELECT res.uid art_uid, count(1) AS art_num FROM " +
    "(SELECT a.uid, a.feed_id, b.category_id FROM feed a INNER JOIN feedid_category b ON a.feed_id = b.feed_id AND " +
    "a.url = 'url:/card/detail' AND a.date between '" + startTime + "' and '" + endTime + "') " +
    "res " +
    "GROUP BY res.uid"
  val insert_result_hsql = "INSERT overwrite TABLE partition_user_selected partition(date='" + today + "') "

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
    * 执行sql，转换为dataframe
    */
  def sqlToFrame(sqlContext: HiveContext) = {
    sqlContext.sql(use_moji)

    val tagDF = sqlContext.sql(tag_hql)
    tagDF.registerTempTable("t_tag")
    val daysDF = sqlContext.sql(days_hql)
    daysDF.registerTempTable("t_days")
    val articleDF = sqlContext.sql(article_hql)
    articleDF.registerTempTable("t_article")

    tagDF.persist(StorageLevel.MEMORY_ONLY_SER)
    daysDF.persist(StorageLevel.MEMORY_ONLY_SER)
    articleDF.persist(StorageLevel.MEMORY_ONLY_SER)

    (tagDF, daysDF, articleDF)
  }

  /**
    * 停止spark
    */
  def stopSpark() = {
    SparkUtils.sparkStoped(init())
  }

}


object User_selected {

  def setStreamingLogLevels() {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      //      logInfo("Setting log level to [WARN] for streaming example." + " To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }

  val user_selected = new User_selected

  def main(args: Array[String]) {
    val sc = user_selected.init()
    try {
      val sqlContext = new HiveContext(sc)

      val frame_result = user_selected.sqlToFrame(sqlContext)
      val tagDF = frame_result._1
      val daysDF = frame_result._2
      val articleDF = frame_result._3

      /**
        * 三个维度的交集
        */
      val pre_data = tagDF.join(daysDF, tagDF("tag_uid").equalTo(daysDF("days_uid"))).join(articleDF, tagDF("tag_uid").equalTo(articleDF("art_uid")))

      /**
        * 创建筛选用户的的元数据信息，这是对pre_data数据的映射
        */
      val pre_result_row = pre_data.map(row => Row(row(0), row(1), row(3), row(5)))

      val structFields = ArrayBuffer[StructField]()
      structFields.+=(DataTypes.createStructField("uid", DataTypes.StringType, true))
      structFields.+=(DataTypes.createStructField("tag_num", DataTypes.IntegerType, true))
      structFields.+=(DataTypes.createStructField("date_num", DataTypes.IntegerType, true))
      structFields.+=(DataTypes.createStructField("art_num", DataTypes.IntegerType, true))
      val structType = DataTypes.createStructType(structFields.toArray)

      val restDF = sqlContext.createDataFrame(pre_result_row, structType)
      restDF.registerTempTable("result_result")


      sqlContext.sql(user_selected.insert_result_hsql + "select uid,tag_num,date_num,art_num from result_result")

    } catch {
      case e: Exception => {
        throw new SparkException("Spark执行失败.....", e)
      }
    } finally {
      SparkUtils.sparkStoped(sc)
    }
  }
}

package com.mw.test.sparksql.moji

import java.util.Properties

import com.mw.test.common.SparkUtils.SparkUtils
import org.apache.spark.sql.{Row, SQLContext, SaveMode}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkContext, SparkException}

import scala.collection.mutable

/**
  * Created by wei.ma on 2016/9/23.
  */

class User_key_du {

  /**
    * hive-sql
    */
  val use_moji = "use moji"
  val key_du = "insert overwrite table partition_key_du partition(dated='0923_0925') " +
    "select com_part.date_time,com_part.fdate,tempm_uid.pre_uid,com_part.key,com_part.du,com_part.value from " +
    "(select uid pre_uid from profile_uid) tempm_uid " +
    "join " +
    "(select datetime date_time,fdate,uid,key,du,value from commonlog_partly where date between '2016-09-23' and '2016-09-25' and (key='FEED_ARTICLE_STAY_TIME' or key='FEED_ARTICLE_CLICK')) " +
    "com_part on tempm_uid.pre_uid = com_part.uid"

  val data_to_mysql = "select * from partition_key_du where dated='0821_0827' order by date_time"

  /**
    * 初始化spark运行环境
    */
  def init(): SparkContext = {
    val settings = new mutable.HashMap[String, String]()
    settings.put("spark.driver.cores", "3")
    settings.put("spark.executor.memory", "16g")
    settings.put("spark.driver.memory", "6g")
    settings.put("spark.executor.instances", "16")
    settings.put("spark.executor.cores", "3")
    settings.put("spark.driver.maxResultSize", "4g")
    settings.put("spark.akka.frameSize", "100")
    settings.put("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    //    val sc = SparkUtils.sparkEntrance("USER_KEY_DU", "yarn-client", settings)
    val sc = SparkUtils.sparkEntrance("FEED_USER_PROFILE", "local", settings)
    sc

  }

  /**
    * 执行sql，转换为dataframe
    */
  def sqlToFrame(sqlContext: HiveContext) = {
    sqlContext.sql(use_moji)
    //    sqlContext.sql(key_du)
    //    println("key_du = " + key_du)
    val key_duDF = sqlContext.sql(data_to_mysql)

    key_duDF.registerTempTable("key_du")

    key_duDF.persist(StorageLevel.MEMORY_ONLY_SER)
    key_duDF
  }

}


object User_key_du {
  val user_key_du = new User_key_du

  def main(args: Array[String]) {
    val sc = user_key_du.init()
    try {
      val sqlContext = new HiveContext(sc)
      /*本地测试*/

      val key_du_file_path = "E:\\mojiworkspace\\moji-work\\src\\main\\resources\\data\\key-du\\key-du.txt"

      val sqlCtx = new SQLContext(sc)
      import sqlCtx.implicits._
      val KEY_DUDF = sc.textFile(key_du_file_path).map(_.split(",")).map(keydu => T_KEY_DU(keydu(0), keydu(1), keydu(2).toInt, keydu(3), keydu(4), keydu(5), keydu(6))).toDF()
      val pre_result_row = KEY_DUDF.map(row => Row(row(0), row(1), row(2), row(3), row(4), row(5), row(6)))

      println("pre_result_row = " + pre_result_row.collect.toBuffer)
      val prop = new Properties()
      prop.put("user", "root")
      prop.put("password", "123456")
      KEY_DUDF.write.mode("append").jdbc("jdbc:mysql://127.0.0.1:3306/mojitest", "user_key_du", prop)
      /*本地测试*/
    } catch {
      case e: Exception => {
        throw new SparkException("Spark执行失败.....", e)
      }
    } finally {
      SparkUtils.sparkStoped(sc)
    }
  }
}
case class T_KEY_DU(date_time: String, fdate: String, uid: Int, key_s: String, du_s: String, value_s: String, dated: String)


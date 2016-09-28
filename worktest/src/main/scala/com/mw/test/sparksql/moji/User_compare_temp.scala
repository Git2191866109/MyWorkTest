package com.mw.test.sparksql.moji

import com.mw.test.common.SparkUtils.SparkUtils
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{Logging, SparkException}

import scala.collection.mutable

/**
  * Created by wei.ma on 2016/9/19.
  */
class User_compare_temp {

}

object User_compare_temp extends Logging {


  def main(args: Array[String]) {
    //屏蔽不必要的日志显示在终端上
    //    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    /*hive的开发环境*/
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
    val sc = SparkUtils.sparkEntrance("Feed_user_compare", "local", settings)
    val sqlContext = new SQLContext(sc)
    //    val sqlContext = new HiveContext(sc)
    /*开始业务*/
    //    val user_compare = new User_compare
    //  sqlContext.sql("use moji")


    try {
      val schemaString = "uid du"
      val schema = StructType(schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, true)))

      val rowRDD = sc.textFile("E:\\mojiworkspace\\moji-work\\src\\main\\scala\\com\\moji\\work\\user_profile\\s").map(x => {
        val s = x.split("\t")
        (s(0), s(1))
      }).map(p => {
        Row(p._1, p._2)
      })
      val rddpeople2 = sqlContext.createDataFrame(rowRDD, schema)
      val regist_table = rddpeople2.registerTempTable("rddTable")
      val result = rddpeople2.select("uid", "du")
      println(result.collect().toString)
    } catch {
      case e: Exception => {
        logError("Spark执行失败.log....", e)
        throw new SparkException("Spark执行失败.....", e)
      }
    } finally {
      SparkUtils.sparkStoped(sc)
      logInfo("程序停止......")
    }
  }


}
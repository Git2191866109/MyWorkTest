package com.mw.test.sparksql.moji

import com.mw.test.common.SparkUtils.SparkUtils
import org.apache.spark.sql.hive.HiveContext

import scala.collection.mutable

/**
  * Created by wei.ma on 2016/9/19.
  */
object User_compare {
  def init(): HiveContext = {
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
    val sc = SparkUtils.sparkEntrance("Feed-compare", "yarn-client", settings)
    val sqlContext = new HiveContext(sc)
    sqlContext
  }

  def main(args: Array[String]) {
    //屏蔽不必要的日志显示在终端上
    val hiveContext = init()
    hiveContext.sql("use moji")
    val hsql = "select tempm_uid.pre_uid uid,feed_temp.feed_id feedid from (select uid pre_uid from profile_uid) tempm_uid join (select feed_id,uid from feed where date='2016-09-04' ) feed_temp on feed_temp.uid = tempm_uid.pre_uid"
    val sqlData = hiveContext.sql(hsql)
    val result_map = sqlData.map(lines => {
      ("uid:"+lines(0), lines(1))
    }).groupByKey()


    println("result_map isshi   ........." + result_map.collect.toBuffer.toString())
  }

}

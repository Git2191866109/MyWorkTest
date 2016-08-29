package com.mw.test.sparksql.moji

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by wei.ma on 2016/8/25.
  */

object feed_access_click {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("MySQL-Demo")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val url =
      "jdbc:mysql://localhost:3306/mojitest?user=root;password=123456"
    val df = sqlContext
      .read
      .format("jdbc")
      .option("url", url)
      .option("dbtable", "pythonid_test")
      .load()

    df.printSchema()
  }

  //  val conf = new SparkConf().setMaster("yarn-client").setAppName("FEED_ACCESS_CLICK").set("spark.driver.cores", "3")
  //    .set("spark.executor.memory", "14g").set("spark.driver.memory", "6g").set("spark.executor.instances", "16")
  //    .set("spark.executor.cores", "3").set("spark.driver.maxResultSize", "4g").set("spark.akka.frameSize", "100")
  //    .set("spark.rdd.compress", "true").set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

  //  val click_num = sqlContext.sql("select uid from pythonid_test").distinct().count()
  //  println("click_num = " + click_num)
}

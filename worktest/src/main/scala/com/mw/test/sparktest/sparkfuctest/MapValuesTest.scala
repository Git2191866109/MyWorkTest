package com.mw.test.sparktest.sparkfuctest

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by wei.ma on 2016/10/27.
  */
object MapValuesTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("map")
    val sc = new SparkContext(conf)
    val list = List(("mobin",22),("kpop",20),("lufei",23))
    val rdd = sc.parallelize(list)
    /*key不变，每一个人的年龄+2*/
    val mapValuesRDD = rdd.mapValues(_+2)
    mapValuesRDD.foreach(println)

    val flatMapValuesRDD = rdd.flatMapValues(x => Seq(x,"male"))
    flatMapValuesRDD.foreach(println)
  }
}

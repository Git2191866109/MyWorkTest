package com.mw.test.sparktest

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZX on 2016/4/12.
  */
object UrlTopN_1 {

  def main(args: Array[String]) {
    var domains = Array("http://java.itcast.cn", "http://php.itcast.cn", "http://net.itcast.cn")

    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val rddall = sc.textFile("c://itcast.log").map(x=> {
      val lines = x.split("\t")
      (lines(1), 1)
    }).reduceByKey(_+_)

//    val rddphp = rddall.filter(_._1.startsWith("http://php.itcast.cn"))
//    val rphp = rddphp.sortBy(_._2, false).take(2)
//
//    val rddjava = rddall.filter(_._1.startsWith("http://java.itcast.cn"))
//    val rjava = rddjava.sortBy(_._2, false).take(2)

    for(domain <- domains) {
      val result= rddall.filter(_._1.startsWith(domain)).sortBy(_._2, false).take(2).toBuffer
      println(result)
    }

    sc.stop()
  }
}

package com.mw.test.sparktest

import java.net.URL

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZX on 2016/4/12.
  */
object UrlTopN_2 {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val rddall = sc.textFile("c://itcast.log").map(x => {
      val lines = x.split("\t")
      (lines(1), 1)
      //(http://net.itcast.cn/net/video.shtml,500)
    }).reduceByKey(_ + _).map { case (k, v) => {
      val host = new URL(k).getHost
      (host, k, v)
      }
    }.groupBy(_._1).mapValues(_.toList.sortBy(_._3).reverse.take(2)).collect()

    println(rddall.toBuffer)

    sc.stop()

  }

}

package com.mw.test

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by mawei on 2016/6/16.
  */
object WorldCount_Cluser {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("userScala").setMaster("spark://search-91:7077")
    val sc = new SparkContext(conf)
    val textRDD = sc.textFile(args(0)).flatMap(_.split(" ")).map(x => (x, 1)).cache()
    val reductRDD = textRDD.reduceByKey(_ + _)
    val sortRDD = reductRDD.sortBy(_._2, false).saveAsTextFile(args(1))
    sc.stop()
  }
}

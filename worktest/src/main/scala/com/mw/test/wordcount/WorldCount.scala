package com.mw.test.wordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by mawei on 2016/6/16.
  */
object WorldCount {
  def main(args: Array[String]) {
    val path = "E:\\TestWork\\src\\main\\resources\\worldcount"
    val conf = new SparkConf().setAppName("userScala").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val textRDD = sc.textFile(path).flatMap(_.split(" ")).map(x => (x, 1)).cache()
    val reductRDD = textRDD.reduceByKey(_ + _)
    val sortRDD = reductRDD.sortBy(_._2, false)
    print(sortRDD.collect().toBuffer)
    print(sortRDD.take(2).toBuffer)
  }
}

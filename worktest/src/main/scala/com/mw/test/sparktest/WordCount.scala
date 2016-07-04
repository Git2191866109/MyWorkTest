package com.mw.test.sparktest

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZX on 2016/4/10.
  */
  object WordCount {
    def main(args: Array[String]) {
      //创建SparkConf
      val conf = new SparkConf().setAppName("WordCount").setMaster("spark://node-1.itcast.cn:7077")
      //spark提交程序的路口SparkContext
      val sc = new SparkContext(conf)
      sc.addJar("C:\\idea\\BigData\\target\\bigdata-2.0.jar")
      //调用sparkContext的方法操作RDD
      val rdd1 = sc.textFile(args(0))
      val p0 = rdd1.partitions(0)
      val prefer = rdd1.preferredLocations(p0)
      println(prefer)
      val result = rdd1.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_, 1).sortBy(_._2).collect()
      //println(result.toBuffer)
      sc.stop()
    }
}



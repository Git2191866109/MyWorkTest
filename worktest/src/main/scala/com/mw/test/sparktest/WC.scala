package com.mw.test.sparktest

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZX on 2016/4/10.
  */
  object WC {
    def main(args: Array[String]) {
      //创建SparkConf
      val conf = new SparkConf().setAppName("WordCount")
      //spark提交程序的路口SparkContext
      val sc = new SparkContext(conf)
      //调用sparkContext的方法操作RDD
      sc.textFile(args(0)).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_, 1).sortBy(_._2).saveAsTextFile(args(1))
      sc.stop()
    }
}

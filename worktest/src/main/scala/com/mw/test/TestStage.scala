package com.mw.test

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZX on 2016/4/17.
  */
object TestStage {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("WordCount")
    //spark提交程序的路口SparkContext
    val sc = new SparkContext(conf)
    val rdd1 = sc.textFile("hdfs://node-1.itcast.cn:9000/g1").map(x=>x.split(" ")).map(x=>(x(0), x(1)))
    val rdd2 = sc.textFile("hdfs://node-1.itcast.cn:9000/g2").map(x=>x.split(" ")).map(x=>(x(0), x(1)))
    val rdd3 = rdd1.join(rdd2)
    rdd3.saveAsTextFile("hdfs://node-1.itcast.cn:9000/out000")
    sc.stop()
  }
}

package com.mw.test.secondsort

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by mawei on 2016/7/5.
  * 具体实现步骤：
  * 1、按照Ordered和Serial实现自定义排序的key
  * 2、将要进行二次排序的文件加载进来，形成key-value类型的Rdd
  * 3、使用sortBykey基于自定义的key进行二次排序
  * 4、去除掉排序的key，只保留排序的结果
  */
object SecondSort {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("secondSort").setMaster("local")
    val sc = new SparkContext(conf)
    //    sc.setLogLevel("OFF")
    val lines = sc.textFile("E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\secondsort\\sencondsort")
    //    println(lines.collect.toBuffer.toString())
    val pairWithKey = lines.map(line => {
      val arrsx = line.split("  ")
      (new SecondSortKey(arrsx(0).toInt, arrsx(1).toInt), line)
    })
    pairWithKey.collect().foreach(e => {
      println(e)
    })
    /*输出形式*/
 /* (com.mw.test.secondsort.SecondSortKey@1455154c,4  1)
    (com.mw.test.secondsort.SecondSortKey@7343922c,2  5)
    (com.mw.test.secondsort.SecondSortKey@526b2f3e,4  3)
    (com.mw.test.secondsort.SecondSortKey@6f2e1024,1  2)
    (com.mw.test.secondsort.SecondSortKey@536d97f8,1  5)
    (com.mw.test.secondsort.SecondSortKey@3c50ad4b,3  5)
    (com.mw.test.secondsort.SecondSortKey@37496720,3  2)*/
    /*sortByKey必须是key-value形式*/
    val sorted = pairWithKey.sortByKey(true)
    val sortedResult = sorted.map(sortedLine => sortedLine._2)
    sortedResult.collect().foreach(e => {
      println(e)
    })
  }
}

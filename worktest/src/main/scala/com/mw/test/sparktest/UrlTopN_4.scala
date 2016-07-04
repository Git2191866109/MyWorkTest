package com.mw.test.sparktest

import java.net.URL

import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

/**
  * Created by ZX on 2016/4/12.
  */
object UrlTopN_4 {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val data = sc.textFile("c://itcast.log", 3)
    val partArr = data.map(x => {
      val lines = x.split("\t")
      val host = new URL(lines(1)).getHost
      host
    }).distinct().collect()

    val parttioner = new HostPartitioner2(partArr)

//    data.map(x => {
//      val lines = x.split("\t")
//      val host = new URL(lines(1)).getHost
//      (host, lines(1))
//    }).partitionBy(parttioner).map(t => (t._2, 1))
//      .reduceByKey(_+_)

    data.map(x => {
      val lines = x.split("\t")
      (lines(1), 1)
    }).reduceByKey(parttioner, _+_).mapPartitions(it => {
      it.toList.sortBy(_._2).reverse.iterator
    }).saveAsTextFile("c://out000")

//    }).reduceByKey(_ + _).map { case (k, v) => {
//      val host = new URL(k).getHost
//      (host, (k, v))
//    }
//    }
    sc.stop()

  }
}

class HostPartitioner2(arr: Array[String]) extends Partitioner {

  val partMap = new mutable.HashMap[String, Int]()
  var counter = 0

  for(host <- arr){
    partMap += (host -> counter)
    counter += 1
  }

  override def numPartitions: Int = arr.length

  override def getPartition(key: Any): Int = {
    val host = new URL(key.toString).getHost
    partMap.getOrElse(host, 0)
  }
}

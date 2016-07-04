package com.mw.test.sparkstreaming

import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}

/**
  * Created by ZX on 2016/4/18.
  */
object StateFulWordCount {

//  val func = (it: Iterator[(String, Seq[Int], Option[Int])]) => {
//    //it.map(t =>(t._1, t._2.sum + t._3.getOrElse(0)))
//    it.flatMap { case (a, b, c) => Some(b.sum + c.getOrElse(0)).map(x => (a, x)) }
//  }



  val func = (iterator: Iterator[(String, Seq[Int], Option[Int])]) => {
    iterator.map(t =>(t._1, t._2.sum + t._3.getOrElse(0)))
  }



  def main(args: Array[String]) {
    LoggerLevels.setStreamingLogLevels()
    val conf = new SparkConf().setAppName("StateFulWordCount").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(5))
    ssc.checkpoint("c://ck")
    val lines = ssc.socketTextStream("172.16.0.11", 9999, StorageLevel.MEMORY_AND_DISK_SER)
    val results = lines.flatMap(_.split(" ")).map((_, 1)).updateStateByKey(func, new HashPartitioner(ssc.sparkContext.defaultParallelism), true)
    results.print()
    ssc.start()
    ssc.awaitTermination()
  }
}

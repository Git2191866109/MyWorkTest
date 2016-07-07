package com.mw.test.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.api.java.StorageLevels
import org.apache.spark.streaming.{Milliseconds, StreamingContext}

object StreamWordCount {

  def main(args: Array[String]) {
    LoggerLevels.setStreamingLogLevels()
    val conf = new SparkConf().setAppName("StreamWordCount").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Milliseconds(5000))
    val lines = ssc.socketTextStream("172.16.0.11", 9999)
    //val ds = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_)
    //ds.print()

    lines.foreachRDD(rdd => {
      //jdbc
      println("rdd ->" + rdd)
      rdd.foreachPartition(it => {
        println("par -> "  + it)
        it.foreach(x =>println("itcast " + x))
      })
    })
    ssc.start()
    ssc.awaitTermination()
  }
}

package com.mw.test.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Milliseconds, StreamingContext}

object StreamingWordCount {

  def main(args: Array[String]) {
    LoggerLevels.setStreamingLogLevels()
    val conf = new SparkConf().setAppName("StreamingWordCount").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Milliseconds(5000))
    val dstream = ssc.socketTextStream("172.16.0.11", 9999)
    val resutls = dstream.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_)
    resutls.print()
    dstream.foreachRDD(rdd => {
      rdd.foreach(println(_))
    })
    ssc.start()
    ssc.awaitTermination()
  }
}

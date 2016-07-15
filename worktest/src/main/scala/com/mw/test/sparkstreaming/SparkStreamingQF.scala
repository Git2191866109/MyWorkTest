package com.mw.test.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, Milliseconds, StreamingContext}

/**
  * Created by mawei on 16/7/11.
  */
object SparkStreamingQF {
  def main(args: Array[String]) {
    LoggerLevels.setStreamingLogLevels()
    val zkQuorum = "172.31.10.151:2181,172.31.10.152:2181,172.31.10.153:2181";
    val group = "ss";
    val topicss = "testkafkamw";
    val numThread = "4";
    val sparkConf = new SparkConf().setAppName("KafkaWordCount").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    ssc.checkpoint("c://ck1")
    val topicMap = topicss.split(",").map((_, numThread.toInt)).toMap
    val data = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap)
    val mapRdd = data.map(x =>{
      val lines = x._2.toString().split(" ")
      val key = "ahisrd" + "_" + lines(0) + "_" + lines(2)
      (lines(4), (key, x._2))
    })
    val groupBykey = mapRdd.groupByKey()
    val distinct = groupBykey.flatMap(_._2.toList.distinct).groupByKey()
    distinct.print()

//    (iiii,(ahisrd_qf_110,qf qf11 110 1 iiii 1234 10 1111111))
//    (iiii,(ahisrd_qf_222,qf qf11 222 1 iiii 1234 10 1111111.))
//    (wwww,(ahisrd_qf_110,qf qf11 110 1 wwww 1234 10 1111111))
//    (iiii,(ahisrd_qf_110,qf qf11 110 1 iiii 1234 10 1111111))
//    (wwww,(ahisrd_qf_110,qf qf11 110 1 wwww 1234 10 1111111))
//    (aaaa,(ahisrd_qf_110,qf qf11 110 1 aaaa 1234 10 1111111))
    //    val mapRdd = data.map(x => {
    //      val lines = x.toString()split("\01")
    //      val key = "ahisrd" + "_" + lines(0) + "_" + lines(2)
    //      (lines(4), (key, x))
    //    }).groupByKey().flatMap(_._2.toMap)
    //    mapRdd.print()
    //    mapRdd.foreach(println)
    //    //    println(mapRdd.collect().toBuffer)
    //    val mapResult = mapRdd.groupByKey()
    //
    //    mapResult.foreach(println)
    ssc.start()
    ssc.awaitTermination()
  }

}

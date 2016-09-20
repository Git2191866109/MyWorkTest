package com.mw.test.sparktest

import com.mw.test.common.SparkUtils_bak
import kafka.utils.Logging

/**
  * Created by mawei on 16/7/9.
  */
object SparkTest extends Logging {
  def main(args: Array[String]) {
    val sc = SparkUtils_bak.sparkEntrance("appName", "local");
//    val path: String = "/Users/mawei/Documents/ideaworkspace/MyWorkTest/worktest/src/main/resources/source/sparktext"
    val path: String = "E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\source\\sparktext"
    val scRDD = sc.textFile(path)
    //    val flatRDD = scRDD.flatMap(lines => lines.split("\01"))
    /*将去重后,然后按照kw分组,然后flatMap的到tuple的*/
    val mapRdd = scRDD.map(x => {
      val lines = x.split("\01")
      val key = "ahisrd" + "_" + lines(0) + "_" + lines(2)
      (lines(4), (key, x))
    }).distinct().groupByKey().flatMap(_._2.toMap)
    mapRdd.foreach(println)
    //    println(mapRdd.collect().toBuffer)
    val mapResult = mapRdd.groupByKey()

    mapResult.foreach(println)
    //    println(mapRdd.collect().toBuffer)
  }
}

package com.mw.test.sparktest

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZX on 2016/4/12.
  */
object MoblieLocation {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("MoblieLocation").setMaster("local")
    val sc = new SparkContext(conf)
    //
    //    sc.textFile("c://bs_log").map(_.split(",")).map(x => {
    //      val mb = x(0) + "_" + x(2)
    //      (mb, x(1), x(3))
    //    })
    val rdd1 = sc.textFile("c://bs_log").map(x => {
      val arr = x.split(",")
      val mb = arr(0) + "_" + arr(2)
      val flag = arr(3)
      var time = arr(1).toLong
      if (flag == "1") time = -time
      (mb, time)
    }).reduceByKey(_ + _).sortBy(_._2, false).groupBy(_._1.split("_")(0)).map(t => {
      val m = t._1
      val bs = t._2.toList(0)._1.split("_")(1)
      val time = t._2.toList(0)._2
      (bs, (m, time))
    })

    val rdd2 = sc.textFile("c://lac_info.txt").map(x => {
      val arr = x.split(",")
      val bs = arr(0)
      (bs, (arr(1), arr(2)))
    })

    //(16030401EAFB68F1E3CDF819735E1C66,((18688888888,87600),(116.296302,40.032296)))
    val fr = rdd1.join(rdd2).map(t => (t._2._1._1, t._1, t._2._2, t._2._1._2)).collect()
    println(fr.toBuffer)
    sc.stop()
  }

}

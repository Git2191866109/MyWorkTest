package com.mw.test.sparktest

/**
  * Created by ZX on 2016/4/12.
  */
import java.text.SimpleDateFormat

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZX on 2016/3/27.
  */
object TT {

  def main(args: Array[String]) {
    val sdf = new SimpleDateFormat("yyyyMMddHHmmss")
    val conf = new SparkConf().setAppName("UserLocation").setMaster("local")
    val sc = new SparkContext(conf)
    //读取基站信息
    val rdd1 = sc.textFile("c://bs_log").map(_.split(",")).map(x => {
      //（手机号_基站ID，时间，事件类型）
      (x(0) + "_" + x(2), sdf.parse(x(1)).getTime, x(3))
      //按 手机号_基站ID 分组
    }).groupBy(_._1).mapValues(_.map(
      //建立连接基站的时间设置为负的
      x => if (x._3.toInt == 0) x._2.toLong else -x._2.toLong
    )).mapValues(_.sum).groupBy(_._1.split("_")(0)).map { case (k, v) => {
      //分组后二次排序
      (k, v.toList.sortBy(_._2).reverse(0))
    }
    }.map(t => (t._1, t._2._1.split("_")(1), t._2._2))

    //读取基站信息
    val rdd2 = sc.textFile("c://lac_info.txt").map(_.split(",")).map(x => (x(0), (x(1), x(2))))
    //join都获取基站的经纬度
    rdd1.map(t => (t._2, (t._1, t._3))).join(rdd2).map(x => (x._2._1._1, x._1, x._2._2._1, x._2._2._2)).saveAsTextFile("c://out0")

    sc.stop()
  }

}

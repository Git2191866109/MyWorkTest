package com.mw.test.sparktest.sparkfuctest

import com.mw.test.common.SparkUtils.SparkUtils

/**
  * Created by wei.ma on 2016/9/20.
  */
object MapTest {
  def main(args: Array[String]) {
    val sc = SparkUtils.sparkEntrance("maptest", "local", null)
    val a = sc.parallelize(List("dog", "salmon", "salmon", "rat", "elephant"), 1)
    val b = a.map(_.length)
    val c = a.zip(b)
    /*ArrayBuffer((dog,3), (salmon,6), (salmon,6), (rat,3), (elephant,8))*/
    println(c.collect.toBuffer)
    /*ArrayBuffer(3, 6, 6, 3, 8)*/
    println(b.collect.toBuffer)

  }
}

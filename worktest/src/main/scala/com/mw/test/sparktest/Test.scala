package com.mw.test.sparktest

import com.mw.test.common.SparkUtils_bak

/**
  * Created by mawei on 2016/7/13.
  */
object Test {
  def main(args: Array[String]) {
//    val sc = SparkUtils.sparkEntrance("app","local")
    val str = "qf Aqf11 A110 A1 Aiiii A1234 A10 A111111"

    val result =str.split(" ").toList
    print(result.toBuffer)
  }
}

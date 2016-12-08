package com.mw.test.sparktest

import com.mw.test.common.SparkUtils.SparkUtils
import com.mw.test.common.SparkUtils_bak

/**
  * Created by mawei on 2016/7/13.
  */
object Test {
  def main(args: Array[String]) {
    val sc = SparkUtils.sparkEntrance("app","local",null)
    val result =sc.textFile("E:\\mojiworkspace\\MyWorkTest\\worktest\\src\\main\\scala\\com\\mw\\test\\sparktest\\dfvgbh").map(str => str.split(":"))
      .map(x=>(x(0),x(1).replace("[","").replace("]","").split(",").map(_.toDouble)))
    result.collect.foreach(x => println(x._1,x._2.toBuffer))
  }
}

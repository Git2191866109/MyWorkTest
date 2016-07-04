package com.mw.test.sparktest

/**
  * Created by ZX on 2016/4/12.
  */
object TestMap {


  //scala Map 现实生活中的           RDD  虚拟世界中
  def main(args: Array[String]) {
    val m = Map(("a",1), ("b",2))
    //m.map(t => (t._1, t._2 + 1))
    val m1 = m.map {
      case (k, v) => (k, v + 1)
    }
    println(m1)
  }
}

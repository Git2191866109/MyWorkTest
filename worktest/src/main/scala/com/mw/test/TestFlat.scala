package com.mw.test

/**
  * Created by ZX on 2016/4/18.
  */
object TestFlat {

  def main(args: Array[String]) {
    val arr = Array(1,2,3,4,5)
    val r1 = arr.map(_ * 2)

    val lst = List(Some(1), Some(2), Some(3), Some(4), Some(5))
    println(lst.flatMap(x => x.map(_ * 2)))
  }
}

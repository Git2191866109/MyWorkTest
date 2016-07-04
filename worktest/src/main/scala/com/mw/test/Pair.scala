package com.mw.test

/**
  * Created by ZX on 2016/4/12.
  */

//Pari<T extends Comparable>
class Pair[T <: Comparable[T]](val first: T, val second: T) {
  def bigger() = {
    if(first.compareTo(second) > 0) first else second
  }
}

object Pair {
  def main(args: Array[String]) {
    val p = new Pair[String]("hadoop", "spark")
    println(p.bigger())
  }
}

package com.mw.test.scalatest

import java.util.Calendar

/**
  * Created by wei.ma on 2016/8/26.
  */
object ScalaException {
  def main(args: Array[String]) {
    val then = null
    val now = Calendar.getInstance()

    try {
      now.compareTo(then)
    } catch {
      case e: NullPointerException => println("One was null!"); System.exit(-1)
//      case unknown => println("Unknown exception " + unknown); System.exit(-1)
    } finally {
      println("It all worked out.")
      System.exit(0)
    }
  }
}

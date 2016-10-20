package com.mw.test.scalademos.traitTest

/**
  * Created by wei.ma on 2016/10/13.
  */
trait Logger {
  def log(msg: String): Unit = {
    println("log" + msg)
  }
}

class ConCreateLogger extends Logger {
  def conCreateLog: Unit = {
    log("It's log!!!")
  }
}

object ConCreateLogger extends App {
  val logger = new ConCreateLogger
  logger.conCreateLog
}
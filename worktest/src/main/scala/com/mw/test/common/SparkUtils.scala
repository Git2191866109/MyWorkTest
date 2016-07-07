package com.mw.test.common

import org.apache.spark.{Logging, SparkConf, SparkContext, SparkException}

/**
  * Created by mawei on 2016/7/6.
  */
object SparkUtils extends Logging {
  /**
    * 定义spark程序的入口
    */
  private def sparkEntrance(appName: String, model: String): SparkContext = {
    assert(appName != null, "AppName不能为空")
    assert(model != null, "运行模式不能为空")
    try {
      val conf = new SparkConf().setAppName(appName).setMaster(model)
      val sc = new SparkContext(conf)
      sc
    } catch {
      case e: Exception => {
        throw new SparkException("Spark程序启动失败.....", e)
      }
    }
  }

  /**
    * spark程序stop
    */
  private def sparkStoped(sc: SparkContext): Unit = {
    try {
      sc.stop()
    } catch {
      case e: Exception => {
        throw new SparkException("Spark程序停止失败.....", e)
      }
    }
  }

  def main(args: Array[String]) {
    logInfo("Spark程序启动......")
    val sc = SparkUtils.sparkEntrance("master", "local")
    SparkUtils.sparkStoped(sc)
    logInfo("Spark程序停止......")
  }
}

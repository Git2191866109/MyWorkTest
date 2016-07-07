package com.mw.test.common

import org.apache.spark.{Logging, SparkConf, SparkContext, SparkException}

/**
  * Created by mawei on 2016/7/6.
  */
object SparkUtils extends Logging {
  /**
    * 定义spark程序的入口
    */
  private def setSparkEntrance(appName: String, model: String): SparkContext = {
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

  def sparkEntrance(appName: String, model: String): SparkContext = {
    if (appName == null && model == null) {
      defaultSparkEntrance()
    }
    setSparkEntrance(appName, model)
  }

  /**
    * 上面的方法太死了，如果不传，就报错，希望可以有一个默认的default方法，对不传的用户进行默认的设置
    */
  private def defaultSparkEntrance(): SparkContext = {
    sparkEntrance("AppName", "local")
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
    //    val sc = SparkUtils.sparkEntrance("master", "local")
    val sc = SparkUtils.defaultSparkEntrance();
    SparkUtils.sparkStoped(sc)
    logInfo("Spark程序停止......")
  }
}

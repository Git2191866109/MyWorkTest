package com.mw.test.common.SparkUtils

import org.apache.spark.{Logging, SparkConf, SparkContext, SparkException}

import scala.collection.mutable

/**
  * Created by mawei on 2016/9/8
  */

object SparkUtils extends Logging {

  /**
    *
    * @param appName           程序名称
    * @param model             运行方式
    * @param extendsParameters 扩展参数
    * @return
    */
  private def setSparkEntrance(appName: String, model: String, extendsParameters: mutable.HashMap[String, String]): SparkContext = {
    assert(appName != null, "AppName不能为空")
    assert(model != null, "运行模式不能为空")
    try {
      val conf = new SparkConf().setAppName(appName).setMaster(model)
      if (extendsParameters != null) {
        extendsParameters.foreach {
          case (k, v) => conf.set(k, v)
        }
      }
      val sc = new SparkContext(conf)
      sc
    } catch {
      case e: Exception => {
        throw new SparkException("Spark程序启动失败.....", e)
      }
    }
  }

  /**
    * 默认的default方法，对不传的用户进行默认的设置
    */
  private def defaultSparkEntrance(): SparkContext = {
    sparkEntrance("AppName", "local", null)
  }

  /**
    * spark程序stop
    */
  def sparkStoped(sc: SparkContext): Unit = {
    try {
      sc.stop()
    } catch {
      case e: Exception => {
        throw new SparkException("Spark程序停止失败.....", e)
      }
    }
  }

  def sparkEntrance(appName: String, model: String, extendsParameters: mutable.HashMap[String, String]): SparkContext = {
    if (appName == null && model == null && extendsParameters == null) {
      defaultSparkEntrance()
    }
    setSparkEntrance(appName, model, extendsParameters)
  }

  def main(args: Array[String]) {
    logInfo("Spark程序启动......")

    val settings = new mutable.HashMap[String, String]()
    settings.put("spark.driver.cores", "3")
    settings.put("spark.executor.memory", "14g")
    settings.put("spark.driver.memory", "6g")
    settings.put("spark.executor.instances", "16")
    settings.put("spark.executor.cores", "3")
    settings.put("spark.driver.maxResultSize", "4g")
    settings.put("spark.akka.frameSize", "100")
    settings.put("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

    val sc = SparkUtils.sparkEntrance("master", "local", settings);
    SparkUtils.sparkStoped(sc)

    logInfo("Spark程序停止......")
  }
}

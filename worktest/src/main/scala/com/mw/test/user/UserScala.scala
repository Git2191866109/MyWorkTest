package com.mw.test.user

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable
import scala.util.parsing.json.JSONArray


/**
  * Created by mawei on 16/6/16.
  * {"city":"130200.0","cookie_id":"GAR68451","province":"130203.0","region":"130000.0","user_id":"PER111765"}
  */
object UserScala {
  def main(args: Array[String]) {
    val path = "/Users/mawei/Documents/ZhGeTianXiaWork/zhgtxworkspace/TestWork/src/main/scala/com/mw/test/user/part-00000"
    val conf = new SparkConf().setAppName("userData").setMaster("local[1]")
    val sc = new SparkContext(conf)
    /*读取文件RDD*/
    val rddText = sc.textFile(path)
    /*切分文件RDD*/
    val rddSplit = rddText.map(line => line.replace("\\N", "").split("\01").toBuffer).cache()
    /**
      * 将map阶段的数据拿出来,过滤掉长度为0的
      * 得到 ArrayBuffer(ArrayBuffer(PER38565, DEA10173, 130000.0, 130700.0, 130702.0), ArrayBuffer(PER39392, DEA10335, 150000.0, 150600.0, 150602.0), ArrayBuffer(PER41406, DEA10786, 110000.0, 110300.0, 110101.0), ArrayBuffer(PER41553, DEA10830, 140000.0, 141000.0, 141002.0))
      * 然后在map就相当于取出每一个元素
      */
    val rddFilter = rddSplit.filter(_.length != 0).map(t => {
      val map = new mutable.HashMap[String, Any]()
      map.put("user_id", t(0))
      map.put("cookie_id", t(1))
      map.put("region", t(2))
      map.put("city", t(3))
      map.put("province", t(4))
      //println()
      /*返回结果*/
      scala.util.parsing.json.JSONObject(map.toMap)
      /*使用json的方式*/
//      val jsonObject = new com.alibaba.fastjson.JSONObject;
    })

    /**
      * 结果输出
      */
    rddFilter.saveAsTextFile("/Users/mawei/Documents/ZhGeTianXiaWork/zhgtxworkspace/TestWork/src/main/scala/com/mw/test/user/out.txt")
    sc.stop()
  }
}

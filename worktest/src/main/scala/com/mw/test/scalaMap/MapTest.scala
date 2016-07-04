package com.mw.test.scalaMap

import java.util
import scala.collection.JavaConversions._

/**
  * Created by mawei on 2016/6/30.
  */
object MapTest {

  def main(args: Array[String]): Unit = {
    /**
      * java 的map  ---------> scala的map
      */
    val map_scala: scala.collection.mutable.Map[String, Object] = new util.HashMap[String, Object]()
    map_scala.put("1", "ss1")
    map_scala.put("2", "ss2")
    map_scala.put("3", "ss3")
    //    for (entry: util.Map.Entry[String, String] <- map_scala()) {
    //      println(entry.getKey)
    //    }
    map_scala.foreach(e => {
      println(e._1 + ",,,," + e._2)
    })
    /*第二种方式*/
    map_scala.foreach {
      case (k, v) => println(k + ",,,," + v)
    }
    /*第三种*/
    val rseul = map_scala.keySet.toArray
    for (i <- 0 to rseul.size - 1) {
      println(map_scala.get(rseul(i)))
    }


  }

}


/**
  * scala 遍历java.util.Map怎么操作？
  */
/**
  * Java Properties转为scala.collection.Map：

  * 　　import scala.collection.JavaConversions.propertiesAsScalaMap
  * 　　val prop: scala.collection.Map[String, String] = System.getProperties();

  * Java Map转为scala.collection.mutable.Map[String, Int]：

  * 　　import scala.collection.JavaConversions.mapAsScalaMap
  * 　　val map: scala.collection.mutable.Map[String, Int] = new TreeMap[String, Int]

  * Scala Map转为Java Map:

  * 　　import scala.collection.JavaConversions.mapAsJavaMap
  * 　　import java.awt.font.TextAttribute._
  * 　　var fs = Map(FAMILY -> "Serif", SIZE -> 12)
  * 　　var fonts = new Font(fs)
  *

  */
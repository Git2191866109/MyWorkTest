package com.mw.test.sparktest.sparkfuctest

import com.mw.test.common.SparkUtils.SparkUtils
import org.apache.spark.SparkEnv

/**
  * Created by wei.ma on 2016/10/27.
  */
object foldByKeyTest {
  def main(args: Array[String]) {
    val sc = SparkUtils.sparkEntrance("foldByKeyTest", "local", null)
    val people = List(("Mobin", 2), ("Mobin", 1), ("Lucy", 2), ("Amy", 1), ("Lucy", 3))
    val rdd = sc.parallelize(people)
    /*先对每个V都加2，再对相同Key的value值相加。*/
    val foldByKeyRDD = rdd.foldByKey(2)(_+_)
    foldByKeyRDD.foreach(println)
  }
}

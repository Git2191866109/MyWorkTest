package com.mw.test.sparktest.sparkfuctest

import com.mw.test.common.SparkUtils.SparkUtils

/**
  * Created by wei.ma on 2016/9/20.
  */
object GroupByKeyTest {
  def main(args: Array[String]) {
    val sc = SparkUtils.sparkEntrance("groupbyKeyLearn", "local", null)
    val words = Array("one", "two", "two", "three", "three", "three")

    val wordPairsRDD = sc.parallelize(words).map(word => (word, 1))
    val wordCountsWithReduce = wordPairsRDD.reduceByKey(_ + _).collect()
    println(wordCountsWithReduce.toBuffer.toString())
    val wordCountsWithGroup = wordPairsRDD.groupByKey().map(t => (t._1, t._2.sum)).collect()
  }
}

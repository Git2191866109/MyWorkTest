package com.mw.test.sparktest.sparkfuctest

import com.mw.test.common.SparkUtils.SparkUtils

/**
  * Created by wei.ma on 2016/10/27.
  * 对两个RDD(如:(K,V)和(K,W))相同Key的元素先分别做聚合，
  * 最后返回(K,Iterator<V>,Iterator<W>)形式的RDD,numPartitions设置分区数，提高作业并行度
  */
object cogroupTest {
  def main(args: Array[String]) {
    val sc = SparkUtils.sparkEntrance("cogroupTest", "local", null)
    val arr = List(("A", 1), ("B", 2), ("A", 2), ("B", 3))
    val arr1 = List(("A", "A1"), ("B", "B1"), ("A", "A2"), ("B", "B2"))
    val rdd1 = sc.parallelize(arr, 3)
    val rdd2 = sc.parallelize(arr1, 3)
    val cogroupRDD = rdd1.cogroup(rdd2)
    cogroupRDD.foreach(println)
    sc.stop
  }
}

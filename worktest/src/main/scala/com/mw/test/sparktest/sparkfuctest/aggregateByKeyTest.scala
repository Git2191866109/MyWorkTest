package com.mw.test.sparktest.sparkfuctest

import com.mw.test.common.SparkUtils.SparkUtils

/**
  * Created by wei.ma on 2016/9/20.
  */
object aggregateByKeyTest {
  def main(args: Array[String]) {
    val sc = SparkUtils.sparkEntrance("aggregateByKey", "local[2]", null)
    val pairRDD = sc.parallelize(List(("cat", 2), ("cat", 5), ("mouse", 4), ("cat", 12), ("dog", 12), ("mouse", 2)), 3) /*这里的3 是 将这个数据分成 3 个 partition*/
    def myfunc(index: Int, iter: Iterator[(String, Int)]): Iterator[String] = {
      iter.toList.map(x => "[partID:" + index + ", val: " + x + "]").iterator
    }
    /*ArrayBuffer([partID:0, val: (cat,2)], [partID:0, val: (cat,5)], [partID:0, val: (mouse,4)], [partID:1, val: (cat,12)], [partID:1, val: (dog,12)], [partID:1, val: (mouse,2)])*/
    println(pairRDD.mapPartitionsWithIndex(myfunc).collect.toBuffer.toString)
    /*ArrayBuffer((dog,12), (cat,17), (mouse,6))*/
    println(pairRDD.aggregateByKey(0)(math.max(_, _), _ + _).collect.toBuffer)
    /*ArrayBuffer((dog,100), (cat,200), (mouse,200))*/
    println(pairRDD.aggregateByKey(100)(math.max(_, _), _ + _).collect.toBuffer)

  }
}

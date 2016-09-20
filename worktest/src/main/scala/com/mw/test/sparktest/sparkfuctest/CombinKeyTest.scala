package com.mw.test.sparktest.sparkfuctest

import com.mw.test.common.SparkUtils.SparkUtils

/**
  * Created by wei.ma on 2016/9/20.
  * 结合combineBykey的源码：
  * 1. 初始化
  * 2. 当前分区内的数据聚合
  * 3. 分区都进行mergeValue后,接下来就是对分区间进行合并了
  */
object CombinKeyTest {
  def main(args: Array[String]) {
    val sc = SparkUtils.sparkEntrance("CombinKeyTest", "local", null)
    val initialScores = Array(("Fred", 88.0), ("Fred", 95.0), ("Fred", 91.0), ("Wilma", 93.0), ("Wilma", 95.0), ("Wilma", 98.0))
    val data = sc.parallelize(initialScores)
    type MVType = (Int, Double) //定义一个元组类型(科目计数器,分数)
    val pre_data = data.combineByKey(
        /*score => (1, score)，88.0 =>(1,88.0) 我们把分数作为参数,并返回了附加的元组类型*/
        score => (1, score),
        /*这里的c1就是createCombiner初始化得到的(1,88.0)*/
        (c1: MVType, newScore) => (c1._1 + 1, c1._2 + newScore),
        /*"Fred"可能是个学霸,他选修的科目可能过多而分散在不同的分区中。所有的分区都进行mergeValue后,接下来就是对分区间进行合并了,分区间科目数和科目数相加分数和分数相加就得到了总分和总科目数*/
        (c1: MVType, c2: MVType) => (c1._1 + c2._1, c1._2 + c2._2)
      )
    /*总结果*/
    val result = pre_data.map { case (name, (num, socre)) => (name, socre / num) }.collect
    /*ArrayBuffer((Wilma,95.33333333333333), (Fred,91.33333333333333))*/
    println("result = " + result.toBuffer)
  }
}

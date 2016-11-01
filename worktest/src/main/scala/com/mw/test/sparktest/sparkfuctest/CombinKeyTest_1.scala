package com.mw.test.sparktest.sparkfuctest

import com.mw.test.common.SparkUtils.SparkUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by wei.ma on 2016/9/20.
  * 结合combineBykey的源码：
  * 1. 初始化
  * 2. 当前分区内的数据聚合
  * 3. 分区都进行mergeValue后,接下来就是对分区间进行合并了
  * 统计男性和女生的个数，并以（性别，（名字，名字....），个数）的形式输出
  */
//Partition1:
//K="male"  -->  ("male","Mobin")  --> createCombiner("Mobin") =>  peo1 = (  List("Mobin") , 1 )
//K="male"  -->  ("male","Kpop")  --> mergeValue(peo1,"Kpop") =>  peo2 = (  "Kpop"  ::  peo1_1 , 1 + 1 )    //Key相同调用mergeValue函数对值进行合并
//K="female"  -->  ("female","Lucy")  --> createCombiner("Lucy") =>  peo3 = (  List("Lucy") , 1 )
//
//Partition2:
//K="male"  -->  ("male","Lufei")  --> createCombiner("Lufei") =>  peo4 = (  List("Lufei") , 1 )
//K="female"  -->  ("female","Amy")  --> createCombiner("Amy") =>  peo5 = (  List("Amy") , 1 )
//
//Merger Partition:
//K="male" --> mergeCombiners(peo2,peo4) => (List(Lufei,Kpop,Mobin))
//K="female" --> mergeCombiners(peo3,peo5) => (List(Amy,Lucy))
object CombinKeyTest_1 {
    def main(args: Array[String]) {
      val conf = new SparkConf().setMaster("local").setAppName("combinByKey")
      val sc = new SparkContext(conf)
      val people = List(("male", "Mobin"), ("male", "Kpop"), ("female", "Lucy"), ("male", "Lufei"), ("female", "Amy"))
      val rdd = sc.parallelize(people)
      val combinByKeyRDD = rdd.combineByKey(
        (x: String) => (List(x), 1),
        (peo: (List[String], Int), x: String) => (x :: peo._1, peo._2 + 1),//::Adds an element at the beginning of this list.
        (sex1: (List[String], Int), sex2: (List[String], Int)) => (sex1._1 ::: sex2._1, sex1._2 + sex2._2))//:::Adds the elements of a given list in front of this list.
      combinByKeyRDD.foreach(println)
      sc.stop()
  }
}

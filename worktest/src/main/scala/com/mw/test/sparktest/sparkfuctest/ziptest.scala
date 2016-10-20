package com.mw.test.sparktest.sparkfuctest

/**
  * Created by wei.ma on 2016/10/11.
  */
object ziptest {
  def main(args: Array[String]) {
    val numbers = List(0, 1, 2, 3, 4, 5)
    val series = List(0, 1, 2, 3, 4)

    /**
      * zip 函数将传进来的两个参数中相对应位置上的元素组成一个pair数组。如果其中一个参数元素比较长，那么多余的参数将会被删掉
      */
    println(numbers zip series)

    /**
      * zipAll 和zip 类似，但是如果其中一个元素个数比较少，那么将用默认的元素填充
      */
    val xs = List(1, 2, 3)
    val ys = List('a', 'b')
    val zs = List("I", "II", "III", "IV")

    val x = 0
    val y = '_'
    val z = "_"
    println(xs.zipAll(ys, x, y))
    println(xs.zipAll(zs, x, z))

    /**
      * zipped
      */
    val values = List.range(1, 5)
    println(values)
    println((values, values).zipped toMap)
    println((values, values).zipped map (_ * _) sum)

    /**
      * zipWithIndex 将元素和其所在的下标组成一个pair
      */
    val zipWithIndexs = Seq(0, 1, 1, 2, 3, 5, 8, 13)
    val zipWithIndexsssss = Array(0, 1, 1, 2, 3, 5, 8, 13)
    println(zipWithIndexs.zipWithIndex)
    println(zipWithIndexsssss.zipWithIndex.toList)

    /**
      * unzip 将一个元祖的列表转变成一个列表的元祖
      */
    val seriesIn = Seq(0, 1, 1, 2, 3, 5, 8, 13)
    val fibonacci = seriesIn.zipWithIndex
    println(fibonacci)
    println(fibonacci.unzip)
    println(fibonacci.unzip _1)
    println(fibonacci.unzip _2)

    val yuansu = List((1,3))
    println(yuansu.unzip)
  }
}

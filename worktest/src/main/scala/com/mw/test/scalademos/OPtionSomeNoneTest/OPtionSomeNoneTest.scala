package com.mw.test.scalademos.OPtionSomeNoneTest

/**
  * Created by wei.ma on 2016/10/19.
  */
object OPtionSomeNoneTest extends App {
  def showCapital(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?没有值"
  }

  /**
    * Option类型的值通常作为Scala集合类型（List,Map等）操作的返回类型
    * Option有两个子类别，Some和None
    * 当程序回传Some的时候，代表这个函式成功地给了你一个String，而你可以透过get()函数拿到那个String，
    * 也可以选用另外一个方法，getOrElse。这个方法在这个Option是Some的实例时返回对应的值，传入getOrElse的参数实际上是默认返回值。
    * 如果程序返回的是None，则代表没有字符串可以给你。
    */
  val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo", "China" -> "Beijing")
  /*控制台返回：Option[String] = Some(Paris)*/
  println("capitals get 'France' =" + capitals.get("France"))
  println("capitals get 'France' =" + capitals.get("France").get)
  println("capitals get 'France' =" + showCapital(capitals.get("France")))
  /*Option[String] = None*/
  println("capitals get 'France' =" + capitals.get("North Pole"))
  println("capitals get 'France' =" + showCapital(capitals.get("North Pole")))

  /**
    * Option[T]
    * Scala里Option[T]实际上是一个容器，就像数组或是List一样，你可以把他看成是一个可能有零到一个元素的List。
    * 当你的Option里面有东西的时候，这个List的长度是1（也就是 Some），而当你的Option里没有东西的时候，它的长度是0（也就是 None）。
    */
  val map1 = Map("key1" -> "value1")
  val value1 = map1.get("key1")
  val value2 = map1.get("key2")
  /*如果我们把Option当成一般的List来用，并且用一个for循环来走访这个Option的时候，
  如果Option是None，那这个for循环里的程序代码自然不会执行，于是我们就达到了「不用检查Option是否为None这件事。*/
  def printContentLength(x: Option[String]): Unit = {
    for (c <- x) {
      println(c.length)
    }
  }

  printContentLength(value1)
  printContentLength(value2)
  value1.map(_.length).map("length: " + _).foreach(println)
  value2.map("length: " + _.length).foreach(println)
}


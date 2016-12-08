package com.mw.test.boysgirls

class MissRight[T : Ordering] {

  def choose(first: T, second : T)(implicit ord : T => Ordered[T]): T = {
    if(first > second) first else second
  }

  def select(first: T, second : T) : T = {
    val ord = implicitly[Ordering[T]]
    import Ordered.orderingToOrdered
    if(first > second) first else second
  }
}

object  MissRight {
  def main(args: Array[String]) {
    import MyPredef._
    val mr = new MissRight[Girl]
    val g1 = new Girl("a", 90, 20)
    val g2 = new Girl("b", 90, 18)

    println(mr.select(g1, g2).name)
    println(mr.choose(g1, g2).name)
  }
}

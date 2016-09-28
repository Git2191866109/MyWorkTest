package com.mw.test.scalademos.selftest

/**
  * Created by wei.ma on 2016/9/28.
  */
class Outer {
  /*self => 这句相当于给this起了一个别名为self*/
  /*self不是关键字，可以用除了this外的任何名字命名(除关键字)*/
  self =>
  val v1 = "Scala"

  class Inner {
    println(self.v1)
    v1
  }

  def pinjie = self.v1 + this.v1

}

object Outer {
  val outer = new Outer

  def main(args: Array[String]) {
    println(outer.v1)
    println(outer.pinjie)
  }
}
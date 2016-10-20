package com.mw.test.scalademos.traitTest

/**
  * Created by wei.ma on 2016/10/13.
  */
trait Friend {
  val name: String

  def accomPany() = println("Your frient " + name + " accompanies you")
}

trait Guard {
  val name: String

  def guard() = println(name + " is guarding you!")
}
/*关键字with就可以混入更多的trait*/
class Dog(val name: String) extends Friend with Guard {
  /*重写trait的方法*/
  override def accomPany() = println(name + " accompanies you")
}

object Dog extends App {
  new Dog("Bob").accomPany()
  val bob_1: Friend = new Dog("Hello")
  bob_1.accomPany()
  val bob = new Dog("DouBi")
  bob.guard()
}
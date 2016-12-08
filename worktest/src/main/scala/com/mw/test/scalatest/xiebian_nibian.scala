//package com.mw.test.scalatest
//
///**
//  * Created by wei.ma on 2016/11/1.
//  */
//class xiebian_nibian {
//
//}
//
//class Animal {}
//
//class Bird extends Animal {}
//
////协变
////class Covariant[T](t: T) {
////  val cov = new Covariant[Bird](new Bird)
////  /*c不能赋值给c2，因为Covariant定义成不变类型。*/
////  val cov2: Covariant[Animal] = cov
////}
//
////协变
//// 因为Covariant定义成协变类型的，所以Covariant[Bird]是Covariant[Animal]的子类型，所以它可以被赋值给c2
//class Covariant[+T](t: T) {
//  val cov = new Covariant[Bird](new Bird)
//  val cov2: Covariant[Animal] = cov
//}
//
////逆变
////这里Contravariant[-T]定义成逆变类型，所以Contravariant[Animal]被看作Contravariant[Animal]的子类型，故c可以被赋值给c2。
//class Contravariant[-T](t: T) {
//  val c: Contravariant[Animal] = new Contravariant[Animal](new Animal)
//  val c2: Contravariant[Bird] = c
//}
//
//// 下界
////出错信息为 "Covariant type T occurs in contravariant position in type T of value t"。
////class Consumer[+T](t: T) {
////  def use(t: T) = {}
////}
////为了在方法的参数中使用类型参数，你需要定义下界
////使用[U >: T], 其中T为下界, 表示T或T的超类
//class ConsumerLow[+T](t: T) {
//  def use[U >: T](u: U) = {
//    println(u)
//  }
//}
//
////上界upper bounds
//class ConsumerUpper[-T](t: T) {
//  def get[U <: T](): U = {
//    new U
//  }
//}
//
//class Consumer[-S,+T]() {
//  def m1[U >: T](u: U): T = {new T} //协变，下界
//  def m2[U <: S](s: S): U = {new U} //逆变，上界
//}
//class Test extends App {
//  val c:Consumer[Animal,Bird] = new Consumer[Animal,Bird]()
//  val c2:Consumer[Bird,Animal] = c
//  c2.m1(new Animal)
//  c2.m2(new Bird)
//}
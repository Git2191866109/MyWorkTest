package com.mw.test.scalademos.traitTest

/**
  * Created by wei.ma on 2016/10/13.
  */
/*定义一个抽象类Checker，实现一些在检查中的通用的行为*/
abstract class Check {
  def check(): String = "Checked Application Details..."
}

/**
  * 每一类基础性的检查创建一个trait
  */
trait CreditCheck extends Check {
  override def check(): String = "Checked Credit..." + super.check()
}

trait BalanceCheck extends Check{
  override def check(): String = "Checked Balance..." + super.check()
}

trait EmploymentCheck extends Check {
  override def check(): String = "Checked Employment..." + super.check()
}

trait CriminalRecordCheck extends Check {
  override def check(): String = "Check Criminal Records..." + super.check()
}

object Check extends App{
  val apartmentApplication = new Check with CreditCheck with BalanceCheck

  println(apartmentApplication check)
}
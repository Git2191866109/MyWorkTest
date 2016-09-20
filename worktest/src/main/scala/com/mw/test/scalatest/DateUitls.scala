package com.mw.test.scalatest

import java.text.{DecimalFormat, SimpleDateFormat}
import java.util.{Calendar, Date}

/**
  * Created by wei.ma on 2016/9/8.
  */
object DateUitls {

  def getToday: String = {
    var now: Date = new Date()
    var dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    var hehe = dateFormat.format(now)
    hehe
  }

  def getYesterday(): String = {
    var dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    var cal: Calendar = Calendar.getInstance()
    cal.add(Calendar.DATE, -1)
    var yesterday = dateFormat.format(cal.getTime())
    yesterday
  }

  def getNowWeekStart(): String = {
    var period: String = ""
    var cal: Calendar = Calendar.getInstance();
    var df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    //获取本周一的日期
    period = df.format(cal.getTime())
    period
  }

  def getNowWeekEnd(): String = {
    var period: String = ""
    var cal: Calendar = Calendar.getInstance();
    var df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //这种输出的是上个星期周日的日期，因为老外把周日当成第一天
    cal.add(Calendar.WEEK_OF_YEAR, 1) // 增加一个星期，才是我们中国人的本周日的日期
    period = df.format(cal.getTime())
    period
  }

  def getLastWeekEnd(): String = {
    var period: String = ""
    var cal: Calendar = Calendar.getInstance();
    var df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    //获取本周一的日期
    period = df.format(cal.getTime())
    period
  }

  def getLastWeekStart(): String = {
    var period: String = ""
    var cal: Calendar = Calendar.getInstance();
    var df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //这种输出的是上个星期周日的日期，因为老外把周日当成第一天
    cal.add(Calendar.WEEK_OF_YEAR, -1) // 增加一个星期，才是我们中国人的本周日的日期
    period = df.format(cal.getTime())
    period
  }

  def timeFormat(time: String): String = {
    var sdf: SimpleDateFormat = new SimpleDateFormat("HH:mm:ss")
    var date: String = sdf.format(new Date((time.toLong * 1000l)))
    date
  }

  //核心工作时间，迟到早退等的的处理
  def getCoreTime(start_time: String, end_Time: String) = {
    var df: SimpleDateFormat = new SimpleDateFormat("HH:mm:ss")
    var begin: Date = df.parse(start_time)
    var end: Date = df.parse(end_Time)
    var between: Long = (end.getTime() - begin.getTime()) / 1000 //转化成秒
    var hour: Float = between.toFloat / 3600
    var decf: DecimalFormat = new DecimalFormat("#.00")
    decf.format(hour) //格式化

  }

  //时间戳是秒数，需要乘以1000l转化成毫秒
  def DateFormat(time: String): String = {
    var sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    var date: String = sdf.format(new Date((time.toLong * 1000l)))
    date
  }

  def getNowMonthStart(): String = {
    var period: String = ""
    var cal: Calendar = Calendar.getInstance();
    var df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    cal.set(Calendar.DATE, 1)
    period = df.format(cal.getTime()) //本月第一天
    period
  }

  def getNowMonthEnd(): String = {
    var period: String = ""
    var cal: Calendar = Calendar.getInstance();
    var df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    cal.set(Calendar.DATE, 1)
    cal.roll(Calendar.DATE, -1)
    period = df.format(cal.getTime()) //本月最后一天
    period
  }

  def main(args: Array[String]) {
    //    println("今天是：" + getToday)
    //    println("昨天是：" + getYesterday)
//        println("获取本周开始日期：" + getNowWeekStart)
        println("获取上周开始日期：" + getLastWeekStart)
        println("获取上周末的时间：" + getLastWeekEnd)
//        println("获取本周末的时间：" + getNowWeekEnd)
    //    println("本月的第一天：" + getNowMonthStart)
    //    println("本月的最后一天：" + getNowMonthEnd)
    //    //    println("核心工作时间，迟到早退等的的处理：" + getCoreTime())
    //    print("\n")
    //    print(timeFormat("1436457603"))
    //    print("\n")
    //    print(DateFormat("1436457603"))


  }
}

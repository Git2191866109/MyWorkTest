package com.mw.test.boysgirls

/**
  * Created by ZX on 2016/4/15.
  */
class Boy(val name: String, val faceValue : Int) extends Comparable[Boy]{

  override def compareTo(o: Boy): Int = {
    this.faceValue - o.faceValue
  }
}

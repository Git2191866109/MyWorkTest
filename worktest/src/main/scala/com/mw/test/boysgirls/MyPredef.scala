package com.mw.test.boysgirls

object MyPredef {

  implicit def girl2Ordered(girl: Girl) = new Ordered[Girl] {
    override def compare(that: Girl): Int = {
      if(girl.faceValue == that.faceValue) {
        that.age - girl.age
      } else {
        girl.faceValue - that.faceValue
      }
    }
  }

  implicit object orderingGirl extends Ordering[Girl] {
    override def compare(x: Girl, y: Girl): Int = {
      if(x.faceValue == y.faceValue) {
        y.age - x.age
      } else {
        x.faceValue - y.faceValue
      }
    }
  }

}

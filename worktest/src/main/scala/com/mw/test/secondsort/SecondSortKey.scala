package com.mw.test.secondsort

/**
  * Created by mawei on 2016/7/5.
  * 自定义二次排序
  */
class SecondSortKey(val firstKey: Int, val secondKey: Int) extends Ordered[SecondSortKey] with Serializable {
  override def compare(other: SecondSortKey): Int = {
    if (this.firstKey - other.firstKey != 0) {
      this.firstKey - other.firstKey
    } else {
      this.secondKey - other.secondKey
    }
  }
}

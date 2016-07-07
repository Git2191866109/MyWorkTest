package com.mw.test.sparksql

import com.mw.test.common.SparkUtils
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by mawei on 16/6/21.
  */
object MySparkSQLWindowFucs {
  def main(args: Array[String]) {
    /**
      * 1.通过HiveContext可以直接操作Hive中的数据
      * 基于HiveContext可以使用sql和hql,对hive进行操作
      * 2.通过saveAsTable的方式,把DataFrame中的数据保存到Hive的数据仓库中
      * 3.直接可通过HiveContext.table的方法直接加载Hive中的表而生成DataFrame
      */
    val sc = SparkUtils.sparkEntrance("MySparkSQLWindowFucs", "spark://search-91:7077")
    val hiveContext = new HiveContext(sc)
    /**
      * 使用名称为hive的数据,接下来的所有的表操作都位于这个库中
      */
    /**
      * 使用子查询的方式完成目标数据的提取,在目标数据内部使用窗口row_number来进行分组排序:
      * Partition by :指定窗口函数分组key
      * order by:分组后进行排序
      */
    val result = hiveContext.sql("select * from ods_t_store")
    /*在Driver的控制台上打印结果*/
    result.show()

    /**
      * 把数据保存在Hive数据仓库中
      */
    result.saveAsTable("sortedResultScores")
  }
}

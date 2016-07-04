package com.mw.test.sparksql

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by mawei on 16/6/21.
 */
object SparkSQLWindowFucs {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SparkSQL2Hive").setMaster("spark://search-91:7077")
    /**
     * 通过传入SparkConf实例来定制Spark运行程序的具体参数
     */
    val sc = new SparkContext(conf)

    /**
     * 企业中绝大多数是采用hive来做为数据仓库的
     * 1.通过HiveContext可以直接操作Hive中的数据
     * 基于HiveContext可以使用sql和hql,对hive进行操作
     * 2.通过saveAsTable的方式,把DataFrame中的数据保存到Hive的数据仓库中
     * 3.直接可通过HiveContext.table的方法直接加载Hive中的表而生成DataFrame
     */
    val hiveContext = new HiveContext(sc)

    /**
     * 使用名称为hive的数据,接下来的所有的表操作都位于这个库中
     */
    hiveContext.sql("use test_mq")
    hiveContext.sql("Drop table if exists scores")
    hiveContext.sql("Create table if not exists scores (name String,score Int)" +
      "Row Format Delimited Fields Terminated By ' ' Lines Terminated By '\\n'")
    /*把要处理的数据导入要处理的表中*/
    hiveContext.sql("Load Data Local Inpath '/home/bigdata/topNGroup.txt' Into Table scores")
    /**
     * 使用子查询的方式完成目标数据的提取,在目标数据内部使用窗口row_number来进行分组排序:
     * Partition by :指定窗口函数分组key
     * order by:分组后进行排序
     */
    val result = hiveContext.sql("Select name,score " +
      "From (" +
      "Select " +
      "name," +
      "score," +
      "row_number() over (Partition by name Order By score Desc) rank" +
      "from scores " +
      ") sub_scores " +
      "where rank <=4")
    /*在Driver的控制台上打印结果*/
    result.show()

    /**
     * 把数据保存在Hive数据仓库中
     */
    hiveContext.sql("Drop table if exists sortedResultScores")
    result.saveAsTable("sortedResultScores")
  }
}

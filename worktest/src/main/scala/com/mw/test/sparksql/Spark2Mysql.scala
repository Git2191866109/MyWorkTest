package com.mw.test.sparksql

import java.util.Properties

import org.apache.spark.sql.{SaveMode, SQLContext}
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by wei.ma on 2016/8/29.
  */
class Spark2Mysql {

}

object Spark2Mysql {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("MySQL-Demo")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val jdbcDF = sqlContext.read.format("jdbc")
    val jdbc_Read = jdbcDF.options(Map("url" -> "jdbc:mysql://127.0.0.1:3306/mojitest", "driver" -> "com.mysql.jdbc.Driver", "dbtable" -> "pythonid_test", "user" -> "root", "password" -> "123456"))
    /*load方法返回的是一个dataframe对象*/
    //    val df = jdbc_Read.load()
    //        df.show()
    //        df.select("uid").show()
    //        df.select(df("uid"), df("value")).show()
    /*执行写入mysql的操作*/

    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "123456")
    val sqlcommand = "select * from pythonid_test"
    val dataResult = sqlContext.sql(sqlcommand).write.mode(SaveMode.Overwrite).jdbc("jdbc:mysql://127.0.0.1:3306/mojitest", "pythonid_test", prop)
    sc.stop()


  }
}
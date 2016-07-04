package com.mw.test.ips

import java.sql.DriverManager

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ZX on 2016/4/12.
  */
object JdbcRDDDemo {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("JdbcRDDDemo").setMaster("local[2]")
    val sc = new SparkContext(conf)
    def getConnection() = {
      Class.forName("com.mysql.jdbc.Driver").newInstance()
      DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata", "root", "123456")
    }
    val jdbcRDD = new JdbcRDD(
      sc,
      getConnection,
      "SELECT * FROM ta where id >= ? AND id <= ?",
      1, 4, 2,
      rs => {
        val id = rs.getInt(1)
        val code = rs.getString(2)
        (id, code)
      }
    )

    val jrdd = jdbcRDD.collect()
    println(jdbcRDD.collect().toBuffer)
    sc.stop()
  }
}

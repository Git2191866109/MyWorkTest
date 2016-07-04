package com.mw.test.ips

import java.sql.{DriverManager, PreparedStatement, Connection}

import org.apache.spark.{SparkContext, SparkConf}

/**
  * 将RDD的计算结果写入到MySQL中
  * CREATE TABLE user (id bigint(20) NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, age int(10) DEFAULT NULL, PRIMARY KEY (id)) DEFAULT CHARSET=utf8
  */
object RDD2MySQL {

  val data2MySQL = (iterator: Iterator[(String, Int)]) => {
    var conn: Connection = null
    var ps : PreparedStatement = null
    val sql = "INSERT INTO user (name, age) VALUES (?, ?)"
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata", "root", "123456")
      iterator.foreach(line => {
        ps = conn.prepareStatement(sql)
        ps.setString(1, line._1)
        ps.setInt(2, line._2)
        ps.executeUpdate()
      })
    } catch {
      case e: Exception => println("Mysql Exception")
    } finally {
      if (ps != null)
        ps.close()
      if (conn != null)
        conn.close()
    }
  }

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("RDD2MySQL").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(List(("zhangsan", 18), ("lisi", 20), ("王五", 23)))
    data.foreachPartition(data2MySQL(_))
    sc.stop()
  }
}

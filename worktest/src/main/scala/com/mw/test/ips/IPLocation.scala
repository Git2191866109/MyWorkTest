package com.mw.test.ips

import java.sql.{Connection, Date, DriverManager, PreparedStatement}

import org.apache.spark.{SparkConf, SparkContext}

/**
  * CREATE TABLE location_info (id bigint(20) NOT NULL AUTO_INCREMENT, location varchar(255) NOT NULL, counts int(10) DEFAULT NULL, accesse_date DATE DEFAULT NULL, PRIMARY KEY (id)) DEFAULT CHARSET=utf8
  */

object IPLocation {

  def ip2Long(ip: String): Long = {
    val fragments = ip.split("[.]")
    var ipNum = 0L
    for (i <- 0 until fragments.length){
      ipNum =  fragments(i).toLong | ipNum << 8L
    }
    ipNum
  }

  def binarySearch(lines: Array[(String, String, String)], ip: Long) : Int = {
    var low = 0
    var high = lines.length - 1
    while (low <= high) {
      val middle = (low + high) / 2
      if ((ip >= lines(middle)._1.toLong) && (ip <= lines(middle)._2.toLong))
        return middle
      if (ip < lines(middle)._1.toLong)
        high = middle - 1
      else {
        low = middle + 1
      }
    }
    -1
  }

  val data2MySQL = (iterator: Iterator[(String, Int)]) => {
    var conn: Connection = null
    var ps : PreparedStatement = null
    val sql = "INSERT INTO location_info (location, counts, accesse_date) VALUES (?, ?, ?)"
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata", "root", "123456")
      iterator.foreach(line => {
        ps = conn.prepareStatement(sql)
        ps.setString(1, line._1)
        ps.setInt(2, line._2)
        ps.setDate(3, new Date(System.currentTimeMillis()))
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
    val conf = new SparkConf().setMaster("local[2]").setAppName("IpLocation")
    val sc = new SparkContext(conf)
    val ipNumRange2LocationRdd = sc.textFile("c:/ip.txt").map(_.split("\\|")).map(x => (x(2).toString, x(3).toString, x(6).toString))
    val ipLogRDD = sc.textFile("c:/ip_log").map(_.split("\\|"))
    //将数据colllect到Driver端
    val ipNumRange2LocationArray = ipNumRange2LocationRdd.collect()
    //将变量广播到其他的Executor
    val broadCastArray = sc.broadcast(ipNumRange2LocationArray)

    val locationAndIp = ipLogRDD.map(_(1)).mapPartitions(it => {
      val arr = broadCastArray.value
      it.map(ip => {
        val ipNum = ip2Long(ip)
        val index = binarySearch(arr, ipNum)
        val t = arr(index)
        (t._3, ip)
      })
    })
    val locationCount = locationAndIp.map(t => (t._1, 1)).reduceByKey(_+_)
    //println(locationCount.collect().toBuffer)
    locationCount.foreachPartition(data2MySQL(_))
    sc.stop()

  }
}


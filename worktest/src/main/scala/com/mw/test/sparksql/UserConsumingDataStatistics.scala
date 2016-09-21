package com.mw.test.sparksql

import java.io.FileWriter

import com.mw.test.common.SparkUtils.SparkUtils
import org.apache.spark.sql.{SaveMode, SQLContext}
import org.apache.spark.storage.StorageLevel

import scala.collection.mutable

/**
  * Created by wei.ma on 2016/9/21.
  */

//define case class for user
case class User(userID: String, gender: String, age: Int, registerDate: String, role: String, region: String)

//define case class for consuming data
case class Order(orderID: String, orderDate: String, productID: Int, price: Int, userID: String)

object UserConsumingDataStatistics {
  val userDataPath = "E:\\testData\\userdata.txt"
  val userConsumerPath = "E:\\testData\\sample_consuming_data.txt"
  val filePath = "E:\\testData\\result\\"

  def main(args: Array[String]) {
    var writer: FileWriter = null
    writer = new FileWriter(filePath, true)
    val settings = new mutable.HashMap[String, String]()
    settings.put("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val ctx = SparkUtils.sparkEntrance("UserConsumingDataStatistics", "local", settings)
    val sqlCtx = new SQLContext(ctx)

    import sqlCtx.implicits._
    //Convert user data RDD to a DataFrame and register it as a temp table
    val userDF = ctx.textFile(userDataPath).map(_.split(" ")).map(u => User(u(0), u(1), u(2).toInt, u(3), u(4), u(5))).toDF()
    userDF.registerTempTable("user")
    //Convert consuming data RDD to a DataFrame and register it as a temp table
    val orderDF = ctx.textFile(userConsumerPath).map(_.split(" ")).map(o => Order(o(0), o(1), o(2).toInt, o(3).toInt, o(4))).toDF()
    orderDF.registerTempTable("orders")
    //cache the DF in memory with serializer should make the program run much faster
    userDF.persist(StorageLevel.MEMORY_ONLY_SER)
    orderDF.persist(StorageLevel.MEMORY_ONLY_SER)

    //The number of people who have orders in the year 2015
    val count = orderDF.filter(orderDF("orderDate").contains("2015")).join(userDF, orderDF("userID").equalTo(userDF("userID"))).count()
    println("The number of people who have orders in the year 2015:" + count)

    //total orders produced in the year 2014
    val countOfOrders2014 = sqlCtx.sql("SELECT * FROM orders where orderDate like '2014%' ").show()
    /*这里的输出路径D:\datatest不能存在，程序运行时会自动创建，否则会报错*/
    //    val countOfOrders2014_write = sqlCtx.sql("SELECT * FROM orders where orderDate like '2014%' ")
    //      .write.mode(SaveMode.Append).save("D:\\datatest")

    //    val countOfOrders2014_write_json = orderDF.select("orderID").write.format("json").mode(SaveMode.Overwrite).save("D:\\datatest\\1.json")


    println("total orders produced in the year 2014:" + countOfOrders2014)
    //Orders that are produced by user with ID 1 information overview
    val countOfOrdersForUser1 = sqlCtx.sql("SELECT o.orderID,o.productID, o.price, u.userID FROM orders o, user u where u.userID = " +
      "1 and u.userID = o.userID").show()
    println("Orders produced by user with ID 1 showed.")

    //Calculate the max,min,avg prices for the orders that are producted by user with ID 10
    val orderStatsForUser10 = sqlCtx.sql("SELECT max(o.price) as maxPrice,min (o.price) as minPrice, avg (o.price) as avgPrice, u.userID FROM orders o, " +
      "user u where u.userID = 10 and u.userID = o.userID group by u.userID")
    println("Order statistic result for user with ID 10:")
    orderStatsForUser10.collect().map(order => "Minimum Price=" + order.getAs("minPrice")
      + ";Maximum Price=" + order.getAs("maxPrice")
      + ";Average Price=" + order.getAs("avgPrice")
    ).foreach(result => println(result))

    /*读数据*/
    val read_data = sqlCtx.read.json("E:\\mojiworkspace\\MyWorkTest\\worktest\\src\\main\\resources\\sparksql\\people.json")
    println("read_data:" + read_data.collect.toBuffer)

  }
}

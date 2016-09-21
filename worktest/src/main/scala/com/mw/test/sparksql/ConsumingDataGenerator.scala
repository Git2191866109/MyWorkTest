package com.mw.test.sparksql

import java.io.FileWriter

import scala.util.Random

/**
  * Created by wei.ma on 2016/9/21.
  * 5 个列 (交易单号, 交易日期, 产品种类, 价格, 用户 ID)
  */
object ConsumingDataGenerator {
  private val FILE_PATH = "E:\\testData\\sample_consuming_data.txt"
  // how many records to be generated
  private val MAX_RECORDS = 1000
  // we suppose only 10 kinds of products in the consuming data
  private val PRODUCT_ID_ARRAY = Array[Int](1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  // we suppose the price of most expensive product will not exceed 2000 RMB
  private val MAX_PRICE = 2000
  // we suppose the price of cheapest product will not be lower than 10 RMB
  private val MIN_PRICE = 10
  //the users number which should be same as the one in UserDataGenerator object
  private val USERS_NUM = 1000

  def main(args: Array[String]): Unit = {
    generateDataFile(FILE_PATH, MAX_RECORDS);
  }

  private def generateDataFile(filePath: String, recordNum: Int): Unit = {
    var writer: FileWriter = null
    try {
      writer = new FileWriter(filePath, true)
      val rand = new Random()
      for (i <- 1 to recordNum) {
        //generate the buying date
        var year = rand.nextInt(16) + 2000
        var month = rand.nextInt(12) + 1
        //to avoid checking if it is a valid day for specific
        // month,we always generate a day which is no more than 28
        var day = rand.nextInt(28) + 1
        var recordDate = year + "-" + month + "-" + day
        //generate the product ID
        var index: Int = rand.nextInt(PRODUCT_ID_ARRAY.length)
        var productID = PRODUCT_ID_ARRAY(index)
        //generate the product price
        var price: Int = rand.nextInt(MAX_PRICE)
        if (price == 0) {
          price = MIN_PRICE
        }
        // which user buys this product
        val userID = rand.nextInt(1000) + 1
        writer.write(i + " " + recordDate + " " + productID
          + " " + price + " " + userID)
        writer.write(System.getProperty("line.separator"))
      }
      writer.flush()
    } catch {
      case e: Exception => println("Error occurred:" + e)
    } finally {
      if (writer != null)
        writer.close()
    }
    println("Consuming Data File generated successfully.")
  }
}

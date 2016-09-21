package com.mw.test.sparksql

import java.io.FileWriter

import scala.util.Random

/**
  * Created by wei.ma on 2016/9/21.
  * 6 个列 (ID, 性别, 年龄, 注册日期, 角色 (从事行业), 所在区域)
  */
object UserDataGenerator {
  private val FILE_PATH = "E:\\testData\\userdata.txt"
  private val ROLE_ID_ARRAY = Array[String]("ROLE001", "ROLE002", "ROLE003", "ROLE004", "ROLE005")
  private val REGION_ID_ARRAY = Array[String]("REG001", "REG002", "REG003", "REG004", "REG005")
  private val MAX_USER_AGE = 60
  private val MAX_RECORDS = 1000

  def main(args: Array[String]) {
    generateDataFile(FILE_PATH, MAX_RECORDS)
  }

  private def generateDataFile(filePath: String, recordNum: Int): Unit = {
    var writer: FileWriter = null
    try {
      writer = new FileWriter(filePath, true)
      val rand = new Random()
      for (i <- 1 to recordNum) {
        //generate the gender of the user
        var gender = getRandomGender
        //
        var age = rand.nextInt(MAX_USER_AGE)
        if (age < 10) {
          age = age + 10
        }
        //generate the registering date for the user
        var year = rand.nextInt(16) + 2000
        var month = rand.nextInt(12) + 1
        //to avoid checking if it is a valid day for specific month
        //we always generate a day which is no more than 28
        var day = rand.nextInt(28) + 1
        var registerDate = year + "-" + month + "-" + day
        //generate the role of the user
        var roleIndex: Int = rand.nextInt(ROLE_ID_ARRAY.length)
        var role = ROLE_ID_ARRAY(roleIndex)
        //generate the region where the user is
        var regionIndex: Int = rand.nextInt(REGION_ID_ARRAY.length)
        var region = REGION_ID_ARRAY(regionIndex)

        writer.write(i + " " + gender + " " + age + " " + registerDate + " " + role + " " + region)
        writer.write(System.getProperty("line.separator"))
      }
      writer.flush()
    } catch {
      case e: Exception => println("Error occurred:" + e)
    } finally {
      if (writer != null)
        writer.close()
    }
    println("User Data File generated successfully.")
  }

  private def getRandomGender(): String = {
    val rand = new Random()
    val randNum = rand.nextInt(2) + 1
    if (randNum % 2 == 0) {
      "M"
    } else {
      "F"
    }
  }
}

package com.mw.test.sparksql

import com.mw.test.common.SparkUtils.SparkUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SaveMode, Row, SQLContext}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
  * Created by wei.ma on 2016/9/20.
  */
object PeopleDataStatistics2 {
  private val schemaString = "id,gender,height"
  val path = "E:\\mojiworkspace\\MyWorkTest\\worktest\\src\\main\\resources\\sparksql\\PeopleDataStatistics2"

  def main(args: Array[String]) {
    val sc = SparkUtils.sparkEntrance("PeopleDataStatistics2", "local", null)
    val peopleDataRDD = sc.textFile(path)
    val sqlCtx = new SQLContext(sc)

    val schema = StructType(schemaString.split(",").map(fieldName => StructField(fieldName, StringType, true)))
    val rowRDD: RDD[Row] = peopleDataRDD.map(_.split("  ")).map(eachRow => Row(eachRow(0).trim, eachRow(1).trim, eachRow(2).trim))
    println("rowRDD = " + rowRDD.collect.toBuffer)

    val peopleDF = sqlCtx.createDataFrame(rowRDD, schema)
    peopleDF.registerTempTable("people")
    println("schema = " + schema.toString())

    /*所有信息*/
    val allPeople = sqlCtx.sql("select id,gender,height from people").show()

    /*男性中身高超过 180cm 的人数*/
    val higherMale180 = sqlCtx.sql("select id,gender,height from people where height >= 180 and gender='M'")
    println("男性中身高超过 180cm 的人数: " + higherMale180.count())

    /*女性中身高超过 170cm 的人数*/
    val higherFemale170 = sqlCtx.sql("select id,gender,height from people where height > 170 and gender='F'")
    println("女性中身高超过 170cm 的人数: " + higherFemale170.count())

    /*人群按照性别分组并统计男女人数*/
    peopleDF.groupBy(peopleDF("gender")).count().show()

    /*类 RDD 转换的方式对 DataFrame 操作来统计并打印身高大于 210cm 的前 50 名男性*/
    peopleDF.filter(peopleDF("gender").equalTo("M")).filter(peopleDF("height") > 210).show(50)
    println("Men whose height is more than 210")
    println("<Display #4>")

    /*对所有人按身高进行排序并打印前 5 名的信息*/
    peopleDF.sort(peopleDF("height").desc).take(5).foreach { row => println(row(0) + "," + row(1) + "," + row(2)) }

    peopleDF.orderBy("height").show()
    peopleDF.orderBy(peopleDF("height").desc).show()

    /*男性的平均身高*/
    peopleDF.filter(peopleDF("gender").equalTo("M")).agg(Map("height" -> "avg")).show()
    println("The Average height for Men")
    println("<Display #6>")

    /*女性身高的最大值*/
    peopleDF.filter(peopleDF("gender").equalTo("F")).agg("height" -> "max").show()
    println("The Max height for Women:")
    println("<Display #7>")

    /*将dataFrame保存成文件:未成功*/
    // TODO
//    val saveOptions = Map("header" -> "true", "path" -> "people.csv")
//    val copyOfpeople = peopleDF.select(peopleDF("id").as("pid"), peopleDF("height"))
//    copyOfpeople.write.format("com.databricks.spark.csv").mode(SaveMode.Overwrite).options(saveOptions).save("E:\\mojiworkspace\\MyWorkTest\\worktest\\src\\main\\resources\\testlaji")

  }
}

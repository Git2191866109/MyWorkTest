package com.mw.test.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by mawei on 2016/7/6.
  */
class SparkSQLJDBC2Mysql {
  def jsonTest(path: String, sqlContext: SQLContext): Unit = {
    sqlContext.read.json(path).show()
  }
  def dataFrameOptions (sc: SparkContext, sqlContext: SQLContext): Unit = {
    val df = sqlContext.read.json("E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\sparksql\\people.txt")
    df.show()
    df.printSchema()
    df.select("name").show()
    df.select(df("name"), df("age") + 1).show()
    df.filter(df("age") > 21).show()
    df.groupBy("age").count().show()
  }

  def string2result(sc: SparkContext, sqlContext: SQLContext): Unit = {
    val people = sc.textFile("E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\sparksql\\people.txt")
    val schemaString = "name age"
    import org.apache.spark.sql.Row;
    import org.apache.spark.sql.types.{StructType, StructField, StringType}
    val schema = StructType(schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, true)))
    val rowRDD = people.map(_.split("  ")).map(p => Row(p(0), p(1).trim))
    val peopleDataFrame = sqlContext.createDataFrame(rowRDD, schema)
    /*注册dataFrame成为一个table*/
    peopleDataFrame.registerTempTable("people")
    val results = sqlContext.sql("SELECT name FROM people")
    results.map(t => "Name: " + t(0)).collect().foreach(println)
  }

  def manuallyDataSource(sc: SparkContext, sqlContext: SQLContext): Unit = {
    val df = sqlContext.read.format("json").load("E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\sparksql\\people_manual.json")
    df.select("name", "age").write.format("parquet").save("namesAndAges.parquet")
  }
}

object SparkSQLJDBC2Mysql {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("MySQL-Demo")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val sparksqljdbc2mysql = new SparkSQLJDBC2Mysql;
    val path = "E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\sparksql\\people.txt"
        sparksqljdbc2mysql.jsonTest(path, sqlContext)
    //    sparksqljdbc2mysql.string2result(sc, sqlContext)
    sparksqljdbc2mysql.dataFrameOptions(sc, sqlContext)
    //    val dataFrame = sqlContext.read.format("jdbc").option("url", "jdbc:mysql://127.0.0.1:3306/sparkdb").option("dbtable", "t_person").option("driver", "com.mysql.jdbc.Driver").option("user", "root").option("password", "123456").option("useSSL","false").load()
    //    val reader = sqlContext.read.format("jdbc");
    //    reader.option("url", "jdbc:mysql://127.0.0.1:3306/sparkdb");
    //    reader.option("dbtable", "t_person");
    //    reader.option("driver", "com.mysql.jdbc.Driver");
    //    reader.option("user", "root");
    //    reader.option("password", "123456");
    //    //    val s = dataFrame.selectExpr("select * from t_person order by age desc limit 4")
    //    //    s.write.json("E:\\work_test\\MyWorkTest\\worktest\\src\\main\\resources\\sparksql")
    //    reader.load().show()
    //    println(reader.load().toJSON.collect().toBuffer)
  }
}

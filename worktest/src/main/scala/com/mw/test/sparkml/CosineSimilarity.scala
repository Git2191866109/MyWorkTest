package com.mw.test.sparkml

import org.jblas.DoubleMatrix

/**
  * Created by wei.ma on 2016/10/25.
  *
  */
object CosineSimilarity {
  //基于jblas实现的相似度计算函数:这里利用了  第三方的jar包：jblas
  def cosineSimilarity(vec1: DoubleMatrix, vec2: DoubleMatrix): Double = {
    vec1.dot(vec2) / (vec1.norm2() * vec2.norm2())
  }
  def main(args: Array[String]) {

    import org.jblas.DoubleMatrix
    val aMatrix1 = new DoubleMatrix(Array(1.0, 2.0, 3.0))
    val aMatrix2 = new DoubleMatrix(Array(0.0, 1.0, 1.0))
    println( cosineSimilarity(aMatrix1, aMatrix2) )


//    val conf = new SparkConf().setAppName("CosineSimilarity").setMaster("local")
//    val sc = new SparkContext(conf)
//    val path = "E:\\mojiworkspace\\MyWorkTest\\worktest\\src\\main\\resources\\source\\ml\\sample_svm_data.txt"
//    // Load and parse the data file.
//    val rows = sc.textFile(path).map { line =>
//      val values = line.split(' ').map(_.toDouble)
//      Vectors.dense(values)
//    }.cache()
//    println(rows.collect.toBuffer.toString)
//    val mat = new RowMatrix(rows)
//    // Compute similar columns perfectly, with brute force.
//    val exact = mat.columnSimilarities()
//
//    // Compute similar columns with estimation using DIMSUM
//    val approx = mat.columnSimilarities(0.1)
//    //
//    val exactEntries = exact.entries.map { case MatrixEntry(i, j, u) => ((i, j), u) }
//    val approxEntries = approx.entries.map { case MatrixEntry(i, j, v) => ((i, j), v) }
//    val MAE = exactEntries.leftOuterJoin(approxEntries).values.map {
//      case (u, Some(v)) =>
//        math.abs(u - v)
//      case (u, None) =>
//        math.abs(u)
//    }.mean()
//
//        println(s"Average absolute error in estimate is: $MAE")
//
//    sc.stop()



  }

}

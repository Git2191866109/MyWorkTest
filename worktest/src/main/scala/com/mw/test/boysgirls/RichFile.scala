package com.mw.test.boysgirls

import java.io.File

import scala.io.Source


object Context {
  //implicit def fileToRichFile(file: File) = new RichFile(file)
  implicit val x : Double = 1000
}

class RichFile {
//  def read() : String = {
//    Source.fromFile(file).mkString
//  }

  def test(i: Int)(implicit a : Int = 10): Int ={
    i + a
  }
}

object RichFile {




  def main(args: Array[String]) {
    // Int => RichInt
    //println(1.to(10))
    //val file = new RichFile(new File("c://words.txt")).read()
    //println(file)
//    import Context._
//    val contents = new File("c://words.txt").read()
//    println(contents)

    import Context._
    val rf = new RichFile
    println(rf.test(5))

  }
}
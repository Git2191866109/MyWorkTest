package com.mw.test.thread

import java.util.concurrent.{ExecutorService, Executors}

/**
  */
object ThreadPool01 {

  def main(args: Array[String]) {
    /*定义线程池*/
    val threadPool: ExecutorService = Executors.newFixedThreadPool(10)
    val task = new Runnable {
      override def run(): Unit = {
        println(Thread.currentThread().getName)
        println("sub:--->submitting task...")
        Thread.sleep(10000)
        println("sub:--->task finished")
      }
    }
    println(Thread.currentThread().getName)
    println("main:----pre")
    threadPool.execute(task)
    println("main:----post")

    //threadPool.shutdown()
    threadPool.shutdownNow()
  }

}

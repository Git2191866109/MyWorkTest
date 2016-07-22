package com.mw.test.scalademos.actor

import scala.actors.Actor


/**
  * Created by ZX on 2016/4/4.
  */
class MyActor extends Actor {

  override def act(): Unit = {
    while (true) {
      receive {
        case "start" => {
          println("starting ...")
          Thread.sleep(5000)
          println(Thread.currentThread().getName)
          println("started")
        }
        case "stop" => {
          println("stopping ...")
          //Thread.sleep(8000)
          println(Thread.currentThread().getName)
          println("stopped ...")
        }
      }
    }
  }
}

class YourActor extends Actor {
  override def act(): Unit = {
    loop {
      react {
        case "start" => {
          println("starting ...")
          Thread.sleep(5000)
          println(Thread.currentThread().getName)
          println("started")
        }
        case "stop" => {
          println("stopping ...")
          Thread.sleep(8000)
          println(Thread.currentThread().getName)
          println("stopped ...")
        }
      }
    }
  }
}


object Boot {

  def main(args: Array[String]) {
    val actor = new MyActor
    actor.start()
    actor ! "start"
    actor ! "stop"
    println("消息发送完成！")

    val a2 = new YourActor
    a2.start()
    a2 ! "start"
    a2 ! "stop"

    val a3 = new YourActor
    a3.start()
    a3 ! "start"


  }
}

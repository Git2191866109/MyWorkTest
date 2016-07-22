package com.mw.test.scalademos.actor

import akka.actor.Actor

/**
  * Scala Actor并发编程
  * 初识Actor
  */
object MyActor1 extends Actor{
  //重新act方法
  def act(){
    for(i <- 1 to 10){
      println("actor-1 " + i)
      Thread.sleep(2000)
    }
  }

  override def receive: Actor.Receive = ???
}

object MyActor2 extends Actor{
  //重新act方法
  def act(){
    for(i <- 1 to 10){
      println("actor-2 " + i)
      Thread.sleep(2000)
    }
  }

  override def receive: Receive = ???
}

object ActorTest extends App{
  //启动Actor
 MyActor1.act();
 MyActor2.act();
}



package com.mw.test.thread

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.{BlockingQueue, LinkedBlockingDeque}

abstract class EventLoop[E](name: String) {

  private val eventQueue: BlockingQueue[E] = new LinkedBlockingDeque[E]()

  private val stopped = new AtomicBoolean(false)

  private val eventThread = new Thread(name) {

    override def run(): Unit = {
      while (!stopped.get) {
        val event = eventQueue.take()
        onReceive(event)
      }
    }
  }

  def start(): Unit = {
    onStart()
    eventThread.start()
  }

  def stop(): Unit = {

  }

  def post(event: E): Unit = {
    eventQueue.put(event)
  }

  protected def onStart(): Unit = {}

  protected def onStop(): Unit = {}

  protected def onReceive: PartialFunction[E, Unit]

  protected def onError(e: Throwable): Unit = {}

}

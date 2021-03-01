package lectures.part3concur

import java.util.concurrent.Executors

object Intro extends App {

  /*
    interface Runnable {
      public void run();
    }
   */
  // JVM threads
  val runnable = new Runnable {
    override def run(): Unit = println("Running in parallel")
  }
  val aThread = new Thread(runnable)

  aThread.start() // gives the signal to the JVM to start a JVM thread
  // create a JVM thread => OS thread
  runnable.run() // doesn't do anything in parallel!
  aThread.join() // blocks until aThread finishes running

  val threadHello = new Thread(() => (1 to 5).foreach(_ => println("Hello")))
  val threadGoodbye = new Thread(() => (1 to 5).foreach(_ => println("Goodbye")))
  threadHello.start()
  threadGoodbye.start()
  // different runs provide different results!

  // executors
  val pool = Executors.newFixedThreadPool(10)
  pool.execute(() => println("something in the thread pool"))

  //  pool.execute(() => {
  //    Thread.sleep(1000)
  //    println("Done after one second")
  //  })
  //
  //  pool.execute(() => {
  //    Thread.sleep(1000)
  //    println("Almost done")
  //    Thread.sleep(2000)
  //    println("Done after two seconds")
  //  })

  //  pool.shutdown()
  //  pool.execute(() => println("Should not appear")) // throws an exeception in the calling thread
  //  pool.shutdownNow()

  def runInParallel = {
    var x = 0

    val thread1 = new Thread(() => {
      x = 1
    })
    val thread2 = new Thread(() => {
      x = 2
    })
    thread1.start()
    thread2.start()
    println(x)
  }

  // race condition issue
  //  for (_ <- 1 to 100 ) runInParallel

  class BankAccount(@volatile var amount: Int) {
    override def toString: String = "" + amount
  }

  def buy(account: BankAccount, thing: String, price: Int): Unit = {
    account.amount -= price
    //    println("I bought " + thing)
    //    println("My account is now " + account)
  }

//  for (_ <- 1 to 10000) {
//    val account = new BankAccount(50_000)
//    val thread1 = new Thread(() => buy(account, "shoes", 3000))
//    val thread2 = new Thread(() => buy(account, "iPhone 12", 4000))
//    thread1.start()
//    thread2.start()
//    Thread.sleep(10)
//    if (account.amount != 43000) println("Here I am " + account.amount)
//    //    println
//  }

  // option #1: use synchronized()
  def buySafe(account: BankAccount, thing: String, price: Int) =
    account.synchronized {
      // no two threads can evaluate this at the same time
      account.amount -= price
      println("I bought " + thing)
      println("My account is now " + account)
    }

  // option #2: use @volatile <--- all reads and writes are synchronized (check BankAccount class)
}

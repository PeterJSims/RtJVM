package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  //  println(x.length) null pointer exception

  // 1. throwing exceptions

  // val aWeirdValue: String = throw new NullPointerException
  // How can I assign String above since the value is "Nothing"?
  // "Nothing" exists in all class hierarchies

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subclasses

  // 2. catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try { // return type AnyVal - could be int or string
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught a RuntimeException")
  } finally {
    // finally does not affect the return type of the expression
    // use for side effects
    println("finally")
  }

  // 3. define your own exceptions
  class MyException extends Exception
  // extend from Exception and use like any other class
  val exception = new MyException
  throw exception
}


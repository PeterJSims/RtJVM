package com.rockthejvm

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object AdvancedFeatures extends App {
  /**
   * lazy evaluation
   */
  lazy val aLazyValue = 2
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy!")
    42
  }
  val eagerValue = lazyValueWithSideEffect + 1
  // useful in infinite collections

  /**
   * "pseudo-collections": Option, Try
   */
  //
  def methodWhichCanReturnNull(): String = "hello, Scala"

  // Below avoids null checks we might do in Java
  val anOption = Option(methodWhichCanReturnNull()) // Some("hello, Scala")
  // option = "collection" which contains at most one element: Some(value) or None

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string: $string"
    case None => "I obtained nothing"
  }

  def methodWhichCanThrowException(): String = throw new RuntimeException

  val aTry = Try(methodWhichCanThrowException())
  // a try - "collection" which either a value if the code went well, or an exception if the code threw one

  val anotherStringProcessing = aTry match {
    case Success(validValue) => "I have obtained a valid string" + validValue
    case Failure(ex) => s"I have obtained an exception: $ex"
  }
  // map, flatMap, filter

  /**
   * Evaluate something on another thread
   * (asynchronous programming)
   */
  val aFuture = Future { // Future.apply
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value.")
    67
  }

  // future is a "collection" which contains a value when it's evaluated
  // future is composable with map, flatMap, and filter

  /**
   * Implicits
   */

  // #1: implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImplicitVal = 46
  println(aMethodWithImplicitArgs) // aMethodWithImplicitArguments(myImplicitInt)

  // #2: implicit conversions
  implicit class MyRichInteger(n: Int){
    def isEven() = n % 2 == 0
  }
  println(23.isEven()) // new MyRichInteger(23).isEven()
}

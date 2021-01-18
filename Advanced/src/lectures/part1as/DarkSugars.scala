package lectures.part1as

import scala.util.Try

object DarkSugars extends App {

  // syntax sugar #1: methods with single parameters
  def singleArgMethod(x: Int) = x + 1

  val description = singleArgMethod {
    // some complex stuff here
    3
  }

  val aTryInstance = Try { // java's try {...}
    throw new RuntimeException
  }

  List(1, 2, 3).map {
    x => x + 1
  }

  // syntax sugar #2: single abstract method pattern
  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }
  val anotherInstance: Action = x => x + 1 // magic happens here to make this convert

  // example: Runnables
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Hello, Scala")
  })

  val anotherThreat = new Thread(() => println("Hello, Scala"))

  // Works for classes with methods implemented as long as just one isn't
  abstract class AbstractType {
    def implemented: Int = 5

    def notImplemented(a: Int)
  }

  val abstractInstance: AbstractType = (a: Int) => a + 5 // built off of notImplemented
  //  abstractInstance.implemented <- would work as normal

  // syntax sugar #3: the :: and #:: methods are special
  val prependedList = 2 :: List(3, 4)
  // 2.::(List(3,4)) <- Normally infix methods would work like that
  // List(3,4).::(2) is what it is doing (?!)

  // scala spec - associativity of method is determined by the LAST CHARACTER
  1 :: 2 :: 3 :: List(4,5)
  List(45).::(3).::(2).::(1)

  class MyStream[T] {
    def -->: (value: T): MyStream[T] = this // real implementation goes here
  }

  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  // syntax sugar #4: multi-word method naming
  class TeenGirl(name: String) {
    def `and then said`(statement: String): String = s"$name said $statement"
  }

  val teen = new TeenGirl("Trish")
  println(teen `and then said` "oh my gosh")

  // syntax sugar #5: infix types
  class Composite[A, B]
  val composite: Composite[Int, String] = ???
  val compositeSugar: Int Composite String = ???

  class -->[A, B]
  val towards: Int --> String = ???

  // syntax sugar #6: update() is very special, much like apply()
  val anArray = Array(1,2,3)
  anArray(2) = 7 // anArray.update(index, value)
  // used in mutable collections - make an update method if you are making your own
  // remember apply() AND update()

  // syntax sugar #7: setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0 // private for OO encapsulation
    def member: Int = internalMember // "getter"
    def member_=(value: Int) = internalMember = value // "setter"
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // rewritten as aMutableContainer.member_=(42)

}

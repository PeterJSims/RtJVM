package com.rockthejvm

object Functional extends App {

  // Scala is OO
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  }

  val bob = new Person("Bob")
  bob(43) // Invoking bob as a function === bob.apply(43)

  /*
    Scala runs on the JVM
    Function programming:
    - compose functions
    - pass functions as args
    - return functions as results

    Conclusion: FunctionX = Function1, Function2, ... Function22
   */

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  // class now works like a function via apply
  simpleIncrementer.apply(23) // 24
  simpleIncrementer(23) // simpleIncrementer.apply(23)

  // ALL SCALA FUNCTIONS ARE INSTANCES OF THESE FUNCTION_X TYPES

  // function with two arguments and String return type
  val stringConcatenator = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  stringConcatenator("I love", " Scala") // "I love Scala"

  // syntax sugar for FunctionX

  val doubler: (Int) => Int = (x: Int) => 2 * x
  doubler(4) // 8
  val doubler2 = (x: Int) => 2 * x // I could even omit types

  /*
    equivalent to the longer:
    val doubler: Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(x: Int) = 2 * x
    }
   */

  // higher-order functions: take functions as args and/or return functions

  val aMappedList: List[Int] = List(1, 2, 3).map(x => x + 1) // HOF
  val aFlatMappedList = List(1, 2, 3).flatMap { x => List(x, 2 * x) } // FlatMap w/ alternative Syntax
  println(aFlatMappedList)

  val aFilteredList = List(1, 2, 3, 4, 5).filter(_ <= 3) // equivalent to x => x <= 3

  // all pairs between numbers 1,2,3 and 'a', 'b', 'c'
  val allPairs = List(1,2,3).flatMap(num => List('a','b', 'c').map(letter => s"$num-$letter"))

  // for comprehensions
  val alternativePairs = for {
    number <- List(1,2,3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  // equivalent to the map/FlatMap chain above!
  println(allPairs)
  println(alternativePairs)

  /**
   * Collections
   */

  // lists
  val aList = List(1,2,3,4,5)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList // List(0,1,2,3,4,5)
  val anExtendedList = 0 +: aList :+ 6 // List(0,1,2,3,4,5,6)

  // sequences
  val aSequence: Seq[Int] = Seq(1,2,3) // Seq.apply(1,2,3)
  val accessedElement = aSequence(1) // the element at index 1: 2

  // vectors: fast Seq implementation
  val aVector = Vector(1,2,3,4,5)

  // sets
  val aSet = Set(1,2,3,4,1,2,3) // Set(1,2,3,4)
  val setHas5 = aSet.contains(5) // false
  val anAddedSet = aSet + 5 // Set(1,2,3,4,5)
  val aRemovedSet = aSet - 3 // Set(1,2,4)

  // ranges
  val aRange = 1 to 1000
  val twoByTwo = aRange.map(x => 2 * x).toList // List(2,4,6,8..., 2000)

  // tuples = groups of values under the same value
  val aTuple = ("Van Halen", "rock", 1978)

  // maps
  val aPhoneBook: Map[String, Int] = Map(
    ("daniel", 4443223),
    "jane" -> 4223345 // ("jane", 4223345)
  )
}

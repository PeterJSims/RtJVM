package lectures.part1as

import scala.annotation.tailrec

object Recap extends App {

  val aCondition: Boolean = false
  val aConditionedVal = if (aCondition) 42 else 65
  // instructions vs expressions

  val aCodeBlock = {
    if (aCondition) 54
    56
  }

  // unit = void
  val theUnit = println("Hello, Scala")

  // functions
  def aFunction(x: Int): Int = x + 1

  // recursion: stack and tail
  @tailrec
  def factorial(n: Int, acc: BigInt = 1): BigInt =
    if (n <= 0) acc
    else factorial(n - 1, acc * n)


  // OOP

  class Animal

  class Dog extends Animal

  val aDog: Animal = new Dog() // OOP polymorphism by subtyping

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("chomp chomp")
  }

  // method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog

  1 + 2
  1.+(2)

  // anonymous classes
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("chomp chomp chomp chomp")
  }

  // generics
  abstract class MyList[+A]

  // singletons and companions
  object MyList

  // case classes
  case class Person(name: String, age: Int)

  // exceptions and try/catch/finally
  //  val throwsException = throw new RuntimeException // Nothing type
  val aPotentialFailure = try {
    throw new RuntimeException
  } catch {
    case e: Exception => s"I caught an exception: $e"
  } finally {
    println("logging here")
  }

  // functional programming
  val incrementer = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  val incrementerFP = (x: Int) => x + 1
  List(1, 2, 3).map(incrementerFP) // HOF
  // map, flatMap, filter

  // for-comprehension
  val combos = for {
    i <- 1 to 3
    c <- "abc"
  } yield i + "-" + c
  println(combos.mkString(" "))

  // Scala collections: Seqs, Arrays, Lists, Vectors, Maps, Tuples
  val aMap = Map(
    "Peter" -> 123,
    "Jenn" -> 456
  )

  // "collections": Options, Try
  val anOption = Some(2)

  // pattern matching
  val x = 2
  val order = x match {
    case 1 => "First"
    case 2 => "Second"
    case 3 => "Third"
    case _ => x + "th"
  }

  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(name, _) => s"Hi, I am $name."
    case _ => "I'm confused"
  }
  println(greeting)


}

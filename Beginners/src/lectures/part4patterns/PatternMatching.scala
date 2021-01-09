package lectures.part4patterns

import scala.util.Random

object PatternMatching extends App {

  //FIRST USE CASE
  // switch on steroids
  val random = new Random(System.nanoTime())

  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "double"
    case 3 => "trips"
    case _ => "something else" // _ = WILDCARD == Default case

  }
  println(x)
  println(description)

  //SECOND USE CASE
  // decompose values
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 25)
  val greeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a years old."
    case _ => "I don't know who I am"
  }
  println(greeting)


  //THIRD USE CASE
  // guards

  def personMatch(person: Person): String = person match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can get into bars for ${21 - a} years."
    case Person(n, a) => s"Hi, my name is $n and I am $a old."
    case _ => "I don't know who I am"
  }

  val lilBilly = Person("Billy", 8)
  println(personMatch(bob))
  println(personMatch(lilBilly))

  // PM on sealed hierarchies

  // case classes come with extractor patterns
  // for right now, just use case classes (99% of use cases)
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = new Dog("Boston Terrier")

  animal match {
    case Dog(breed) => println(s"Matched a dog of the $breed breed")
  }

  // *** don't go too crazy with them ***
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  /*
    PATTERN MATCHING MAIN POINTS
    1. cases are matched in order
    2. what if no cases match? MatchError
    3. type of the PM expression? Unified type of all the types in all the cases
    4. PM works really really well with case classes - use 99% of the time
   */
}

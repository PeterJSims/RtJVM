package com.rockthejvm

object PatternMatching extends App {

  // switch expression equivalent in Scala -> Pattern Matching
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th" // _ is equivalent to default in js/java
  }

  // PM is an EXPRESSION

  //Case class decomposition
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 43) // Person.apply("bob", 43)

  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a years old."
    case _ => "Something else"
  }
  // deconstructing tuples
  val aTuple = ("Slayer", "metal")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre"
    case _ => "I don't understand the context"
  }

  // decomposing lists
  val aList = List(1, 2, 3)
  val listDescription = aList match {
    case List(_, 2, _) => "List containing 2 as its second position"
    case _ => "Unknown list"
  }

  // if PM doesn't match anything, it will throw a MatchError
  // PM will try all cases in sequence

}

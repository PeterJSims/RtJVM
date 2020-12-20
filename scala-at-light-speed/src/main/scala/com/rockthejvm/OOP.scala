package com.rockthejvm

object OOP extends App {

  // class and instance
  class Animal {
    // define fields
    val age: Int = 0

    // define methods
    def eat() = println("I am eating")
  }

  val anAnimal = new Animal

  // inheritance
  class Dog(val name: String = "Fido") extends Animal // constructor definition

  val aDog = new Dog("Lassie")

  // constructor arguments are NOT fields; put a val before the constructor argument first
  aDog.name

  // subtype polymorphism
  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() // the most derived method will be called at runtime

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true // by default public, can restrict via private or protected
    def walk(): Unit
  }

  // interface = ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }


  trait Philosopher {
    def ?!(thought: String): Unit // valid method name
  }

  // single class and multi trait inheritance like java
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("I am eating you, animal")

    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }

  val aCroc = new Crocodile
  aCroc eat (aDog)
  aCroc eat aDog // infix notation = object method argument, only available for methods with ONE argument!
  aCroc ?! "What if we could fly"

  // operators in Scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) // equivalent to above

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("Excuse me, I am actually a pescatarian")
  }

  /*
    What you are telling the compiler
    class Carnivore_Anonymous_35211 extends Carnivore {
    override def eat(animal: Animal): Unit = println("Excuse me, I am actually a pescatarian")
  }
    val dinosaur = new Carnivore_Anonymous_35211
   */

  // singleton object
  object MySingleton { // the only instance of the MySingleton type
    val mySpecialValue = 54421

    def mySpecialMethod(): Int = 54422

    def apply(x: Int): Int = x + 1
  }

  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  MySingleton(65) // .apply allows this - special Scala method naming

  object Animal { // companions - companion object (same name as existing class or trait)
    // companions can access each other's private fields/methods
    // singleton Animal and instances of Animal are different things
    val canLiveIndefinitely: Boolean = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely // "static" fields/methods

  /*
    case classes = lightweight data structures with some boilerplate
    - sensible equals and hash code
    - serialization
    - companion with apply
   */
  case class Person(name: String, age: Int)

  // a case class may be constructed without the keyword "new"
  val bob = Person("Bob", 54) // Person.apply("Bob", 54)

  // exceptions
  try {
    // code that can throw
    val x: String = null
    x.length
  } catch {
    case e: Exception => println(e)
  } finally {
    // executes no matter what
  }

  // generics
  abstract class MyList[T] {
    def head: T

    def tail: MyList[T]
  }

  // using a generic with a concrete type
  val aList: List[Int] = List(1, 2, 3) // List.apply(1,2,3)
  val first = aList.head // int
  val rest = aList.tail
  val aStringList = List("hello", "Scala")
  val firstString = aStringList.head // string

  // Point #1: in Scala we usually operate with IMMUTABLE values/objects
  // Any modification to an object must return ANOTHER object
  /*
    Benefits:
    1) works miracles in multithreaded/distributed environments
    2) helps making sense of the code ("reasoning about")
   */
  val reversedList = aList.reverse // returns a NEW list

  // Point #2: Scala is closest to the OO ideal

}

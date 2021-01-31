package lectures.part1as

object AdvancedPatternMatching extends App {

  val numbers = List(1)
  val description = numbers match {
    case head :: Nil => s"The only element is $head"
    case _ => "Nothing in list"
  }
  println(description)

  /*
    STANDARD STRUCTURES AVAILABLE FOR PATTERN MATCHING
    - constants
    - wildcards
    - case classes
    - tuples
    - some special magic like above
   */

  // How to make own structures compatible

  // ie What if you can't make a case class?
  class Person(val name: String, val age: Int) {} // not a case class

  // make an unapply method! By default, case class's auto case object have one

  object Person {
    def unapply(person: Person): Option[(String, Int)] = {
      if (person.age == 1) None
      else Some((person.name, person.age))
    }

    // you can overload the method as well
    def unapply(age: Int): Option[String] =
      Some(if (age < 21) "minor" else "major")
  }

  val bob = new Person("Bob", 55)
  val baby = new Person("Bob", 1)

  def greeting(person: Person) = person match {
    case Person(n, a) => s"Hello. I am $n and I am $a years old."
    case _ => "I am a baby"
  }

  println(greeting(bob))
  println(greeting(baby))

  // infix patterns
  case class Or[A, B](a: A, b: B) // Our own version of "Either" class
  val either = Or(2, "two")
  val humanDescription = either match {
    //    case Or(number, string) => s"$number is written as $string"
    case number Or string => s"$number is written as $string" // !!!
  }
  println(humanDescription)

  // decomposing sequences
  val vararg = numbers match {
    case List(1, _*) => "starting with 1"
  }

  abstract class MyList[+A] {
    def head: A = ???

    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]

  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val decomposed = myList match {
    case MyList(1, 2, _*) => "Starting with 1, 2"
    case _ => "Something else"
  }
  println(decomposed)

  // custom return types for unapply
  // make anything a return type if it has these two methods:
  // isEmpty: Boolean and get: something
  abstract class Wrapper[T] {
    def isEmpty: Boolean

    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      def isEmpty: Boolean = false

      def get: String = person.name
    }


  }
  println(bob match {
    case PersonWrapper(n) => s"This person's name is $n"
    case _ => "Not a person"
  })
}

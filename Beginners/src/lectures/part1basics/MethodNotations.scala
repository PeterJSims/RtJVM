package lectures.part1basics
import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == this.favoriteMovie

    def hangsOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def unary_! : String = s"$name, what the heck?!"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie."

  }


  var bob = new Person("Bob", "Invasion USA")
  println(bob likes "Invasion USA") // infix notation/ aka operator notation

  // "operators" in scala
  val tom = new Person("Tom", "Lone Wolf McQuade")
  println(bob hangsOutWith tom)

  println(1 + 2)
  println(1.+(2))

  // operators are methods in scala

  // prefix notation
  val x = -1
  val y = 1.unary_- // equivalent to above
  // unary_ prefix works with - + ~ !

  println(!bob)

  // postfix notation - VERY RARELY USED
  println(bob isAlive)

  // apply
  print(bob())
}

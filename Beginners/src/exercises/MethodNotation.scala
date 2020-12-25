package exercises

object MethodNotation extends App{
  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == this.favoriteMovie

    def hangsOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def learns(newThing: String = "Scala"): String = s"${this.name} learns $newThing"

    def unary_! : String = s"$name, what the heck?!"
    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)

    def + (nickname: String) : Person = new Person(s"$name ($nickname)", this.favoriteMovie)

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie."
    def apply(n: Int): String = s"${this.name} watched ${this.favoriteMovie} $n times"

  }

  val peter = new Person("Peter", "Missing In Action")
  val coolerPeter = peter + "dude"
  println(coolerPeter.name)
  val olderPeter = +peter
  println(olderPeter.age)
  println(olderPeter(5))
  println(olderPeter())

}

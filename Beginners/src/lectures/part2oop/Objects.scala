package lectures.part2oop

object Objects {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")

  // equivalent to code in JavaPlayground -- like Python
  object Person { // type + only instance
    val N_EYES = 2

    def canFly = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }

  // PROCESS OF PAIRING OBJECT AND CLASS IN SAME SCOPE - "COMPANIONS"

  class Person(val name: String) {
    // OFTEN, WE WRITE THE SAME OBJECT AND CLASS
    // THIS IS TO SEPARATE INSTANCE LEVEL FUNCTIONALITY FROM "STATIC"/"CLASS" LEVEL
  }

  def main(args: Array[String]): Unit = {


    println(Person.N_EYES)
    println(Person.canFly)

    // SCALA OBJECTS ARE SINGLETON INSTANCES

    val mary = Person
    val john = Person

    println(mary == john) // EQUAL! ONE INSTANCE MEANS ONE SPOT IN MEMORY


    val mary2 = new Person("Mary")
    val john2 = new Person("John")
    println(mary2 == john2)
    // FALSE - THE "NEW" KEYWORD MAKES IT AN INSTANCE
    // OF CLASS, NOT OBJECT


    // using factory method from Person object
    val bobby = Person(mary2, john2)


    // Scala Applications = Scala object with
    // def main(args: Array[String]): Unit (like Java's)
  }
}

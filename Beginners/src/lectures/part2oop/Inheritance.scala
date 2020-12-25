package lectures.part2oop

object Inheritance extends App {

  // Scala has single class inheritance
  class Animal {
    val creatureType = "wild"
    def eat: Unit = println("nom nom nom")
  }

  class Cat extends Animal {
    def crunch: Unit = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int)

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age) // must provide parameters for parent class

  // overriding
  class Dog(override val creatureType: String) extends Animal {
//    override val creatureType = "domestic"
    override def eat: Unit = {
      super.eat
      println("crunch crunch")
    }
  }

  val dog = new Dog("k9")
  dog.eat
  println(dog.creatureType)

  // type substitution - polymorphism
  val unknownAnimal: Animal = new Dog("k9")
  unknownAnimal.eat

  // super - calls parent class like in java/js

  // preventing overrides
  // 1 - use final keyword on member
  // 2 - use final on the entire class
  // 3 - seal the class via "sealed" keyword - "package protected" equivalent

}

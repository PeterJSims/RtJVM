package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String

    def eat: Unit
  }

  //  val animal = new Animal() -- can't instantiate an abstract class

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    override def eat: Unit = println("chomp chomp chomp")
  }

  // traits - have abstract fields/methods but inherit like interfaces in Java
  trait Carnivore {
    def animal(animal: Animal): Unit
    val preferredMeal: String = "meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"

    override def eat: Unit = println("chomp chomp chew")

    override def animal(animal: Animal): Unit = println(s"I am eating a ${animal.creatureType}")
  }

  class Varmint extends Animal {
    override val creatureType: String = "varmint"

    override def eat: Unit = println("chew chew")
  }

  val croc = new Crocodile
  val varmint = new Varmint
  croc.animal(varmint)

  // traits vs abstract classes
  // 1 - traits cannot have constructor parameters
  // 2 - single inheritance of abstract class, multiple of traits (with ... with ...)
  // 3 - traits = behavior, abstract class = thing
}

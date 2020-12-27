package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class - on the spot implementation
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahaha")
  }
  /*
     the above works like this:
     class AnonymousClasses$$anon$1 extends Animal {
       override def eat: Unit = println("hahahaha")
     }
     val funnyAnimal = new AnonymousClasses$$anon$1
  */
  println(funnyAnimal.getClass)

  class Person(name: String){
    def sayHi: Unit = println(s"Hi, my name is ${this.name}, how can I help?")
  }

  // you can override fields on the spot
  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I help?")

  }

}

package lectures.part2oop

object Generics extends App {

  class MyList[A] {
    // use the type A
  }

  class MyMap[Key, Value]

  val listOfInts = new MyList[Int]
  val listOfStrings = new MyList[String]

  // variance problem
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // animalList.add(new Dog) ?
  // solution => we return a list of Animals => add[B >: A](el: B): MyList2[B]

  // three solutions

  // 1. yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A] extends Animal

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // animalList.add(new Dog) NOW POLLUTED

  // 2. No = INVARIANCE
  class InvariantList[A]

  // BELOW WON'T COMPILE
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]

  // 3. ABSOLUTELY NOT = Contravariant
  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A)
  // [] here is generic type A
  // which is a subset of Animal
  val cage = new Cage(new Dog)

  class Car
  //  val newCare = new Cage(new Car) <-- will fail at compile time

  class Cage2[A >: Animal](animal: A)
  // [] here would only take a SUPER type of animal

  class MyList2[+A]{ // covariant type list
    def add[B >: A](el: B): MyList2[B] = ???
    /*
     A = Cat
     B = Animal
     */
  }
}

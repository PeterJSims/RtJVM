package lectures.part2oop

object CaseClasses extends App{

  case class Person(name: String, age: Int)

  // 1. class params are promoted to fields
  val bob = new Person("Bob", 50)
  println(bob.name) // didn't have to be a field

  // 2. sensible toString - great debugging printout
  println(bob)

  // 3. equals and hashCode out of the box implementation
  // makes case classes great for collections

  val bob2 = new Person("Bob", 50)
  println(bob == bob2)

  // 4. case classes have copy method
  val bob3 = bob.copy()
  val bob4 = bob.copy(age = 51) // can use named params in .copy()

  // 5. case classes have companion objects ALREADY
  val thePerson = Person
  val mary = Person("Mary", 23) // apply method on companion object, not class
  // because above works, we usually use .apply on companion object, not
  // new keyword

  // 6. case classes are serializable
  // Akka

  // 7. case classes have extractor patterns
  // CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of Great Britain and Northern Ireland"
  }
}

package lectures.part2oop

object OOBasics extends App{

  val person = new Person("Peter", 37)
//  println(person.name) Doesn't work --> name isn't a val/var
  println(person.age) // works
  println(person.x) // works as well
  person.greet()
  person.greet("Bob")
}

class Person(name: String, val age: Int = 0){

  def this(name: String) = this(name, 0) //secondary constructor but just use default params!

  val x = 2 // field
  println(1+3) // will print from every instantiation

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  def greet(): Unit = println(s"Hi, I am $name")
}

// class parameters are NOT FIELDS
// turn param into field by adding val/var

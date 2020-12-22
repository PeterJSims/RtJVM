package lectures.part1basics

object Strings extends App{

  val str: String = "Hello, I am learning Scala"

  // All Java functions
  println(str.charAt(3))
  println(str.substring(3, 6))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toUpperCase())
  println(str.length)


  // Scala functions
  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z') // +: is prepend, :+ is append
  println(str.reverse)
  println(str.take(2))

  // Scala string interpolators
  val name = "Peter"
  val age = 37

  // S
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age +1} soon"

  // F
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute"

  // raw
  println(raw"This is a \n newline")
}

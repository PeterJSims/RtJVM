package exercises

object WhatIsAFunction extends App{

  val concat: (String, String) => String = (v1: String, v2: String) => v1 + v2

  val superAdder: (Int) => ((Int) => Int) = (x: Int) => (y: Int) => x + y

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried

}

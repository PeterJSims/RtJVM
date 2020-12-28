package lectures.part3functional

object AnonymousFunctions extends App {

  //Anonymous function / Lambda

  // Slightly different anonymous function syntax
  val doubler = (x: Int) => x * 2
  val doubler2: Int => Int = x => x * 2
  println(doubler(4))

  // multiple params in a lambda
  val adder = (a: Int, b: Int) => a + b
  val adder2: (Int, Int) => Int = (a, b) => a + b

  // no params
  val justDoSomething = () => println("yup")

  // LAMBDAS MUST BE CALLED WITH () SINCE THEY ARE JUST FUNCTION VALUES

  justDoSomething // function
  justDoSomething() // function call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // More syntactic sugar - great for chaining HOFs but function def needed
  val niceIncrementer: Int => Int = _ + 1 // equiv to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equiv to (a, b) => a + b
  // each _ is a parameter


  //  Curried adder exercices
  val superAdder = (x: Int) => (y: Int) => x + y
  //OR
  val superAdderSecondSyntax:  (Int) => ((Int) => Int) = x => y => x + y

}

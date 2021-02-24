package lectures.part2afp

object CurriesAndPAF extends App {

  // curried functions
  val superAdder: Int => Int => Int =
    x => y => x + y

  val add3 = superAdder(3) // Int => Int = y => 3 + y
  println(add3(5))
  println(superAdder(3)(5))

  // METHOD!
  def curriedAdder(x: Int)(y: Int): Int = x + y

  // "lifted" to a function value of Int => Int (aka ETA-EXPANSION)
  val add4: Int => Int = curriedAdder(4) // just leaves remaining parameter list
  /*
   YOU HAVE TO ADD THE TYPE LIKE ABOVE FOR CURRIED FUNCTIONS
   IT TELLS THE COMPILER YOU ARE CREATING A FUNCTION,
   NOT CALLING THE RESULT OF ONE
   */

  // functions != methods (JVM limitation)
  def inc(x: Int) = x + 1

  List(1, 2, 3).map(inc) // ETA-expansion
  // map(x => inc(x)) <-- compiler converts to a lambda like this

  // Partial function applications
  val add5 = curriedAdder(5) _ // THE UNDERSCORE DOES THE TYPE FOR YOU!

  // Exercise
  val simpleAddFunction = (x: Int, y: Int) => x + y

  def simpleAddMethod: (Int, Int) => Int = (x: Int, y: Int) => x + y

  def curriedAddMethod(x: Int)(y: Int): Int = x + y

  val add7 = (x: Int) => simpleAddFunction(7, x) // simplest
  val add7_2 = simpleAddFunction.curried(7) // via special method
  val add7_3 = curriedAddMethod(7) _ // PAF version
  val add7_4 = curriedAddMethod(7)(_) // alternate syntax for PAF
  val add7_5 = simpleAddFunction(7, _) // !!! makes compiler call it like add7
  val add7_6 = simpleAddMethod(7, _) // works with method too

  // underscores are powerful
  def concatenator(a: String, b: String, c: String) = a + b + c
  val concatName = concatenator("Hello, I am ", _, ", how are you?")
  println(concatName("Peter")) // x: String => concatenator("Hello", x, 'how are you")
  val fillInTheBlanks = concatenator("Hello, ", _, _ ) // (x,y) => concatenator("Hello", x, y)
  println(fillInTheBlanks("I'm Peter", "and this is Abbie"))


  // Exercise

  def curriedFormatter(s: String)(number: Double): String = s.format(number)
  val numbers = List(Math.PI, Math.E, 1, 9.8, 1.3e-12)

  val simpleFormat = curriedFormatter("%4.2f") _
  val preciseFormat = curriedFormatter("%14.12f") _
  println(numbers.map(simpleFormat))
  println(numbers.map(preciseFormat))
  println(numbers.map(curriedFormatter("%8.6f"))) // compiler does eta-expansion for us when passed to HOF


}

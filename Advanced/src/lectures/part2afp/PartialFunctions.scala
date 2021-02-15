package lectures.part2afp

object PartialFunctions extends App {
  val aFunction = (x: Int) => x + 1 // Function1[Int,Int] === Int => Int

  val pickyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicable

  class FunctionNotApplicable extends RuntimeException

  val aNicerPickyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }
  // {1,2,5} => Int

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value

  println(aPartialFunction(2))

  // Partial Function Utilities
  println(aPartialFunction.isDefinedAt(67))

  // lift
  val lifted = aPartialFunction.lift // turns it into: Int => Option[Int]
  println(lifted(2))
  println(lifted(55)) // would return None now and not crash program

  val partialFunctionChain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  println(partialFunctionChain(2))
  println(partialFunctionChain(45))

  // PFs extend normal functions
  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // HOFs accept partial functions as well
  val aMappedList = List(1, 2, 3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList)

  /*
    NOTE: partial functions can only ONE parameter type
   */

  /*
    Exercises
    1 - construct a PF instance yourself
    2 - dumb implement
   */
  val stringPartialFunction: PartialFunction[String, String] = {
    case "Yes" => "Correct"
    case "No" => "Incorrect"
  }
  println(stringPartialFunction("Yes"))

  val chatbot: PartialFunction[String, String] = {
    case "Hello" => "Beep Boop Hi I am chatbot"
    case "Goodbye" => "Bloop bop I won't stop talking"
    case "Stop" => "Well I'm a sad robot now"
  }
//  scala.io.Source.stdin.getLines().foreach(line => println(s"Chatbot says ${chatbot(line)}"))
}

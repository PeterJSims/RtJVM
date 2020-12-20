package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int)= {
    val x = 5 + 3// Have to give types in functions but NOT usually return type
    a + b
  } // curly braces optional for single action


  println(aFunction("yup ", 5))
  val anExpression = aFunction("yup ", 5)

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction) // No () needed for parameterless functions
  // aParameterlessFunction above is not the function itself like in Python or JS!

  def aRepeatedFunction(aString: String, n: Int): String ={
    // Recursive functions HAVE TO HAVE explicitly stated return types
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  // USE RECURSION FOR LOOPS

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }



}

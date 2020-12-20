package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // Expression- expressions are evaluated to a value
  println(x)
  println(2 + 3 + 4)

  // + - * / & | ^ << >> >>>

  println(1 == x)
  // == != <= < > >=
  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // += -= /= *=   ... side effects
  println(aVariable)

  // Instructions (DO) vs Expressions (VALUE)

  // IF expression in Scala
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionedValue)
  println(if(aCondition) 5 else 3)

  var i = 0
  val aWhile = while(i < 10) {
    println(i)
    i += 1
  }

  // AVOID WHILE LOOPS IN SCALA - TOO IMPERATIVE STYLE

  // EVERYTHING in Scala is an Expression!

  val aWeirdValue = (aVariable = 3) // Unit == void
  println(aWeirdValue)

  // SIDE EFFECTS ARE EXPRESSIONS RETURNING UNIT ()

  // side effects examples: println(), while, reassigning

  // Code blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }
  println(aCodeBlock)
  

}

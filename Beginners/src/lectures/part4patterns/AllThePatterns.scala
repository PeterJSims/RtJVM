package lectures.part4patterns

import exercises.{Cons, EmptyList, MyList}

object AllThePatterns extends App {

  // 1 - constants - "switch on steroids"
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "a string"
    case true => "a boolean"
    case AllThePatterns => "a singleton object"
  }

  // 2 - match anything
  // 2.1 - wildcard

  val matchAnything = x match {
    case _ => "anything"
  }

  // 2.2. variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1, 2)
  val anotherTuple = (1, 1)

  def tupleMatch(a: Int, b: Int) = (a, b) match {
    case (1, 1) => "(1, 1)"
    case (something, 2) => something + 2
  }

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => ""
  }

  //PMS can be NESTED!

  // 4 - case classes - constructor pattern
  // PMs can be nested with Case Classes as well
  val aList: MyList[Int] = Cons(1, Cons(2, EmptyList))
  val matchAList = aList match {
    case EmptyList => "empty"
    case Cons(head, Cons(subhead, subtail)) => ""
  }

  // 5 - list patterns
  val aStandardList = List(1, 2, 3, 4, 40)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => "" // extractor for list
    case List(1, _*) => "" // list of arbitrary length - advanced
    case 1 :: List(_) => "" // infix pattern
    case List(1, 2, 3) :+ 40 => "" // another infix pattern
  }

  // 6 - type specifiers

  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => "" // explicit type specifier
    case _: Int => "here I am"
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList@Cons(_, _) => "" // name binding => use the name in return expression
    case Cons(1, rest@Cons(2, _)) => "" // name binding inside nested patterns
  }

//  // 8 - multi-patterns
//  // chaining patterns via pipe operator
//  val multipattern = aList match {
//    case EmptyList | Cons(0, _) => "" // compound pattern (multi-pattern)
//  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => ""
  }


  /*
    The following shows a JVM problem
   */

//  val numbers = List(1, 2, 3)
//  val numbersMatch = numbers match {
//    case listOfStrings: List[String] => "a list of strings"
//    case listOfNumbers: List[Int] => "a list of numbers"
//    case _ => ""
//  }

  //  println(numbersMatch)
  // SHOWS "a list of strings!
  // Obscure problem caused by JVM backwards compatibility with generics which were added later
  // FAILS BECAUSE OF java removing type parameters after initial match
  // List[String] is TWO checks - JVM reads it just as List in this case

  // called "Type erasure"
  // IDE will tell me this (see highlighted area above)
}

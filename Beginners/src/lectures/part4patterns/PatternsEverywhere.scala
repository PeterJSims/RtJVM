package lectures.part4patterns

object PatternsEverywhere extends App {

  // big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually MATCHES

  /*
    try {
        // code
      } catch (e) {
      e match {
          case e: RuntimeException => "runtime"
          case npe: NullPointerException => "npe"
          case _ => "something else"
        }
      }
   */

  // big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield (10 * x)

  // generators are also based on PATTERN MATCHING
  // it is how the decomposition works
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  // big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple // works because of pattern matching as well
  val head :: tail = list
  println(head)
  println(tail)

  // big idea #4
  // PARTIAL FUNCTION
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case v => v + " is odd"
  } // partial function literal

  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case v => v + " is odd"
    }
  }

  println(mappedList)
  println(mappedList2)

}

package lectures.part3functional

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.filter(_ % 2 == 0))


  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))


  // print all pairs of the following (ie a1, a2... d4)
  val nums = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")


  //  val combos = chars.flatMap(n => nums.map(c => "" + n + c))
  //  val colorCombos = nums.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  //  println(combos)
  //  println(colorCombos)
  //this comprehension does the above colorCombos
  val colorCombos = for {
    n <- nums
    c <- chars
    col <- colors
  } yield (f"${n}${c}-${col}")

  // if println() instead of yield, would print anyway
  println(colorCombos.mkString(" "))

  val withGuards = for {
    n <- nums if n % 2 == 0
    c <- chars if c == 'a'
    col <- colors if col == "white"
  } yield f"${n}${c}-${col}"

  println(withGuards.mkString(" "))

  // syntax overload
  list.map { x =>
    x * 2
  }
}

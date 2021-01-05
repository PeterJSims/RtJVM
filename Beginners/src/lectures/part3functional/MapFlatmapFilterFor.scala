package lectures.part3functional

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int)=> List(x, x+1)
  println(list.flatMap(toPair))


  // print all pairs of the following (ie a1, a2... d4)
  val nums = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  val combos = chars.flatMap(n => nums.map(c => "" + n + c))
  val colorCombos = nums.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combos)
  println(colorCombos)

  // for-comprehensions

  val forCombinations = for {
    n <- nums if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color

  println(forCombinations)

  for {
    n <- nums
  } println(n)

  // syntax overload
  list.map { x=>
    x * 2
  }
}

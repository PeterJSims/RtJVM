package lectures.part3functional

object HOFsCurries extends App{

  // ??? Yikes, hard to make sense of something like this
   val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // Higher Order Functions are functions that either take or return another function
  // HOF examples are map, flatMap, filter in MyList

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1

  println(nTimes(plusOne, 10, 1))

  // Let's do it a bit better
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))


  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried functions
  val superAdder = (x: Int) => (y:Int) => x +  y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(11))
  println(superAdder(4)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}

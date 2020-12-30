package exercises

object HOFsAndCurrying extends App{

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
      x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)

  def compose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  // tests
  def superAdder2: (Int => Int => Int) = toCurry(_ + _ )
  def add4 = superAdder2(4)
  println(add4(17))

  val superAdder: (Int) => ((Int) => Int) = (x: Int) => (y: Int) => x + y
  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)
  println(composed(4))
  println(ordered(4))
}

package exercises

object MapFlatmapFilterForComp extends App {


  abstract class Maybe[+T] {
    def map[B](f: T => B): Maybe[B]
    def flatMap[B](f: T => Maybe[B]): Maybe[B]
    def filter(p: T => Boolean): Maybe[T]
  }

  case object Not extends Maybe[Nothing]{
    override def map[B](f: Nothing => B): Maybe[B] = Not

    override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = Not

    override def filter(p: Nothing => Boolean): Maybe[Nothing] = Not
  }

  case class Has[+T](value: T) extends Maybe[T] {
    override def map[B](f: T => B): Maybe[B] = Has(f(value))

    override def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(value)

    override def filter(p: T => Boolean): Maybe[T] =
      if (p(value)) this
      else Not
  }

  val has3 = Has(3)
  println(has3)
  println(has3.map(_ * 2))
  println(has3.flatMap(x => Has(x % 2 == 0)))
  println(has3.filter(_ % 2 == 0))
}

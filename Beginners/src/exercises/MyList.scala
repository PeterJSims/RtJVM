package exercises

abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](el: B): MyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  // big three FP functions
  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  // concat for flatMap
  def ++[B >: A](list: MyList[B]): MyList[B]

  //hof
  def foreach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

  def fold[B](start: B)(operator: (B, A) => B): B
}

case object EmptyList extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](el: B): MyList[B] = new Cons(el, EmptyList)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = EmptyList

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = EmptyList

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  def foreach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int): EmptyList.type = EmptyList

  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have matching lengths")
    else EmptyList

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start

}

case class Cons[+A](val h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](el: B): MyList[B] = new Cons(el, this)

  def printElements: String =
    if (t.isEmpty) "" + head
    else h + " " + t.printElements

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, EmptyList)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have matching lengths")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)


}


object ListTest extends App {

  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  val clonedListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, EmptyList)))

  val listOfStrings: MyList[String] = new Cons("one", new Cons("two", new Cons("three", EmptyList)))
  println(listOfIntegers)
  println(listOfStrings)

  println(listOfIntegers.map((a: Int) => a * 2).toString)

  println(listOfIntegers.filter((elem: Int) => elem % 2 == 0).toString)

  //    println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(a => new Cons(a, new Cons(a + 1, EmptyList))).toString)


  println(listOfIntegers == clonedListOfIntegers) // TRUE BECAUSE OF CASE CLASSES
  // otherwise we'd have to have a recursive equals method

  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x, y) => y - x))
  //  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + " " + _ ))

  println(listOfIntegers.fold(0)(_ + _))

  //for comprehension tests

  val forCompTest = for {
    n <- listOfIntegers
    str <- listOfStrings
  } yield f"${n}-${str}"

  println(forCompTest)

}

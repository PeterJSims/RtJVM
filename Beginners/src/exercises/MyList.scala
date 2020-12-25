package exercises

abstract class MyList {
  def head: Int

  def tail: MyList

  def isEmpty: Boolean

  def add(el: Int): MyList

  def printElements: String

  override def toString: String = "[" + printElements + "]"

}

object EmptyList extends MyList {
  def head: Int = throw new NoSuchElementException

  def tail: MyList = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add(el: Int): MyList = new Cons(el, EmptyList)

  def printElements: String = ""
}

class Cons(val h: Int, t: MyList) extends MyList {
  def head: Int = h

  def tail: MyList = t

  def isEmpty: Boolean = false

  def add(el: Int): MyList = new Cons(el, this)

  def printElements: String =
    if(t.isEmpty) "" + head
    else h + " " + t.printElements

}


object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)

  println(list)
}

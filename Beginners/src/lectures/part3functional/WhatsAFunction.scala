package lectures.part3functional

object WhatsAFunction extends App {

  // Goal: use functions as first class elements
  // problem: OOP thinking
  // OOP use
  //  val aClass = new Action()
  //  aClass.methodInClass(5)

  val doubler = new MyFunction[Int, Int] {
    override def apply(el: Int): Int = el * 2
  }
  // doubler a value but also a function
  doubler(2)

  // function types = Function1[A,B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder = new ((Int, Int) => Int) {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // Function2[A,B,R] = (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS
  // They are all instances of classes deriving from Function1, Function2, etc
}

//OOP style
class Action {
  def methodInClass(el: Int): String = "I am a method"
}

trait MyFunction[A, B] {
  def apply(el: A): B
}

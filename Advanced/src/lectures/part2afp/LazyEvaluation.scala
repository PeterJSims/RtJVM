package lectures.part2afp

object LazyEvaluation extends App {

  lazy val x: Int = throw new RuntimeException // won't crash when I compile - not used yet

  lazy val y: Int = {
    println("hello")
    42
  }
  println(y)
  println(y) // won't print the second time -- It's been evaluated already!

  // implications:
  def sideEffectCondition: Boolean = {
    println("Boo")
    true
  }

  def simpleCondition: Boolean = false

  lazy val lazyCondition = sideEffectCondition
  println(if (simpleCondition && lazyCondition) "yes" else "no") // "Boo" not printed because it didn't have to be evaluated

  // in conjunction with call by name
  def byNameMethod(n: => Int): Int = n + n + n + 1

  def retrieveMagicValue = {
    // side effect or long computation
    Thread.sleep(1000)
    42
  }

  //  println(byNameMethod(retrieveMagicValue)) // takes three seconds for the three Ns
  // use lazy vals
  def byNameLazyValMethod(n: => Int): Int = {
    lazy val t = n
    t + t + t + 1
  }

  // CALL BY NEED
  println(byNameLazyValMethod(retrieveMagicValue)) // only one second

  // filtering with lazy vals
  def lessThan30(i: Int): Boolean = {
    println(s"Is $i less than 30?")
    i < 30
  }

  def greaterThan20(i: Int): Boolean = {
    println(s"Is $i greater than 20?")
    i > 20
  }

  val numbers = List(1, 25, 40, 5, 23)
  val lt30 = numbers.filter(lessThan30) // List(1, 25, 5, 23)
  val gt20 = lt30.filter(greaterThan20)
  println(gt20)
  println
  // with filter - uses lazy vals under the hood
  val lt30lazy = numbers.withFilter(lessThan30)
  val gt20lazy = lt30lazy.withFilter(greaterThan20)
  //  println(gt20lazy) // No SIDE EFFECTS HAVE HAPPENED - METHODS WEREN'T CALLED
  gt20lazy.foreach(println) // NOTICE HOW THE METHODS WERE CALLED IN PARALLEL AND NOT SUCCESSION

  // !!!
  // for-comprehensions use withFilter with guards
  for {
    a <- List(1, 2, 3) if a % 2 == 0
  } yield a + 1 // == List(1,2,3).withFilter(_%2 == 0).map(_ + 1)

}

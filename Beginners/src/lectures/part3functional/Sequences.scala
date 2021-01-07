package lectures.part3functional

import scala.util.Random

object Sequences extends App {

  //Seq
  val aSequence = Seq(1, 2, 3, 4)
  println(aSequence) // Seq.apply() subtypes to List above
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(Seq(4, 6, 2, 1, 9, 3).sorted)
  println()

  //Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(x => print(s"${x} "))
  println()

  (1 to 5).foreach(_ => println("Hello"))

  //Lists
  val aList = List(1, 2, 3)
  val prependedAppended = 42 +: aList :+ 100
  val nestedList = aList :: aList
  println(prependedAppended)
  println(nestedList) // added whole list to 0 position

  val apples5 = List.fill(5)("apple")
  println(apples5)

  //Arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements.mkString(" "))

  //mutate in place
  threeElements(1) = 2 // syntactic sugar for threeElements.update(1, 2)
  println(threeElements.mkString(" "))
  //  threeElements(4) = 3  compile time failure
  //  println(threeElements.mkString(" "))

  //Arrays and Sequences
  val numberSeq: Seq[Int] = numbers // implicit conversion
  println(numberSeq)

  //Vector -- default for immutable sequences
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1_000_000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // list keeps reference to tails
  // updating an element in the middle takes a long time
  //  println(getWriteTime(numbersList))   <--- 4931077.208
  // depth of the tree is small
  // needs to replace an entire 32-element chunk (not terrible though)
  //  println(getWriteTime(numbersVector)) <--- 2513.719!!

}

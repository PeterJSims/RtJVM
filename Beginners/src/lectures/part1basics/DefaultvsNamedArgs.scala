package lectures.part1basics

object DefaultvsNamedArgs extends App {

  def factorialTailRec(n: Int, acc: Int = 1): Int =
    if(n <= 1) acc
    else factorialTailRec(n-1, n *acc)

  println(factorialTailRec(5))

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")
  savePicture(width= 800, height= 600) // this all works like python

  //

}


package lectures.part1basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  calledByValue(System.nanoTime())
  println("****")
  calledByName(System.nanoTime())


  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int): Unit = println(x)

//  printFirst(infinite, 34) // yikes, stack overflow
  printFirst(34, infinite)
  // How does this work? In printFirst(34, infinite), infinite is never used
  // so it is never evaluated, thus avoiding the stack overflow from infinite recursion
}

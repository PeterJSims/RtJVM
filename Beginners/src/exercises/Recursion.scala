package exercises

import scala.annotation.tailrec

object Recursion extends App{

  @tailrec
  def concatenateTailRec(aString: String, count: Int, acc: String = ""): String =
    if (count <= 0) acc
    else concatenateTailRec(aString, count - 1, aString + acc )

  println(concatenateTailRec("hello", 3))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeHelper(t: Int, isStillPrime: Boolean): Boolean =
      if(!isStillPrime) false
      else if(t <= 1) true
      else isPrimeHelper(t - 1, n % t != 0 && isStillPrime)

    isPrimeHelper(n / 2, true)
  }

  println(isPrime(2003))

  def fibonacci(n: Int): Int = {
    def fibHelper(i: Int, acc1: Int = 1, acc2: Int = 1): Int =
      if(i >= n) acc1
      else fibHelper(i + 1, acc1 + acc2, acc1)

    if (n <=2) 1
    else fibHelper(2)
  }

  println(fibonacci(8))
}

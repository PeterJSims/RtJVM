package exercises

object Functions extends App {

  // 1. Greeting
  def greeting(name: String, age: Int): String = {
    "Hi, my name is " + name + ", and I am " + age + " years old."
  }

  println(greeting("peter", 37))

  // 2. Factorial
  def factorial(n: Int, total: BigInt = 1): BigInt = {
    if (n == 1) 1
    else n * factorial(n - 1)
  }

  println(factorial(10))

  // 3. Fibonacci
  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  println(fibonacci(8))

  // isPrime
  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if(t <=1) true
      else n % t != 0 && isPrimeUntil(t - 1)
    }
    isPrimeUntil(n / 2)
  }
  println(isPrime(37))
  println(isPrime(14))
}

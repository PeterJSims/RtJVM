package exercises

object Calculator extends App {
  def add(x: Int, y: Int): Int = {
    val result = x + y
    if (x > 0 && y > 0 && result < 0) throw new OverflowException
    else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
    else result
  }

  def subtract(x: Int, y: Int): Int = {
    val result = x - y
    if (x > 0 && y < 0 && result < 0) throw new OverflowException
    else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
    else result
  }

  def multiply(x: Int, y: Int): Int = {
    val result = x * y
    if (x > 0 && y > 0 && result < 0) throw new OverflowException
    else if (x < 0 && y < 0 && result < 0) throw new OverflowException
    else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
    else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
    else result
  }

  def divide(x: Int, y: Int): Int = {
    if (y == 0) throw new MathCalculationException
    else x / y
  }

}



class OverflowException extends RuntimeException

class UnderflowException extends RuntimeException

class MathCalculationException extends RuntimeException("Division by Zero")


package exercises

object PatternMatching extends App{

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(n1, n2) =>   show(n1) + " + " + show(n2)
    case Prod(n1, n2) => {
      def maybeShowParentheses(exp: Expr) = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }
      maybeShowParentheses(n1) + " * " + maybeShowParentheses(n2)
    }
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Prod(Sum(Number(2), Number(3)), Number(2))))
}

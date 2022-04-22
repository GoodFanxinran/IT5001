// Start here.

/** The Either[+A, +B] trait is a right-biased disjoint union type that is
  * either a Left[+A] or a Right[+B]. Typically, Left represents the failure
  * path and Right represents the success path.
  *
  * Either should be able to map and ap.
  *
  * When mapping a Right, the Right value is successfully mapped.
  * val e: Either[String, Int] = Right(1)
  * e.map(_ + 1) // Right(2)
  * When mapping a Left, nothing happens.
  * val e: Either[String, Int] = Left("Error!")
  * e.map(_ + 1) // Left("Error!")
  *
  * When ap-ing two Rights, the Right function value is successfully applied
  * on the Right value.
  * val f: Either[Int => String, Int => String] = Right(_.toString)
  * val e: Either[Int, Int] = Right(1)
  * f.ap(e) // Right("1")
  *
  * As long as either of them are Lefts, nothing happens.
  * val f: Either[Int => String, Int => String] = Left(_.toString)
  * val e: Either[Int, Int] = Right(1)
  * f.ap(e) // Left(_.toString)
  */
// TODO Complete the Either Algebraic Data Type.
sealed trait Either[+A, +B]{

  def map[B](f: A => B): Either[E, B] = this match {
    case Left(e) => Left(e)
    case Right(a) => Right(f(a))
  }
 
  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] =
    this match {
      case Left(e) => Left(e)
      case Right(a) => f(a)
    }
 
  def orElse[EE >: E, AA >: A](b: => Either[EE, AA]): Either[EE, AA] =
    this match {
      case Left(_) => b
      case Right(a) => Right(a)
    }
 
  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
    for {
      a <- this
      b1 <- b
    } yield f(a, b1)
}

/** A Functor F[A] can take a function from A => B and be mapped into an F[B].
  */
trait Functor[F[_]]:
  def map[A, B](fa: F[A])(f: A => B): F[B]

/** All Functors have the map method attached to it: myCoolFunctor.map(_ + 1)
  */
extension [F[_]: Functor, A](fa: F[A])
  def map[B](f: A => B): F[B] = summon[Functor[F]].map(fa)(f)

/** With this given instance, Either[A, B] can take a function from B => C and
  * be mapped into an Either[A, C].
  *
  * Example:
  * val e: Either[String, Int] = Right(1)
  * e.map(_.toString + "!") // Right("1!")
  *
  * Mapping on a Left does nothing.
  * val e: Either[String, Int] = Left("Error!")
  * e.map(_ + 1) // Left("Error!")
  */
// Because Either is a type constructor taking two parameters, the left type
// must be fixed to some A (type parameter of eitherFunctor) and the type constructor
// passed into Functor is a type lambda of only one parameter B that produces
// Either[A, B].
given eitherFunctor[A]: Functor[[B] =>> Either[A, B]] with
  def map[B, C](fa: Either[A, B])(f: B => C): Either[A, C] = fa match
    case Left(x)  => Left(x)
    case Right(x) => Right(f(x))

/**
 * An Applicative F[A] can take an F[A => B] and produce an F[B].
 */
// TODO Complete the Applicative typeclass.
trait Applicative[F[_]] extends Functor[F] {
  def pure[A](a: A): F[A]
  def flatMap[A, B](f: F[A => B]): F[A] => F[B]
}

// TODO Create a given instance of the Either Applicative. Use the Either Functor
// instance definition to help you.
// Hint: You should use a type lambda as the argument to Applicative.


// The extension method for Applicatives has been defined for you already.
// To use it, call ap on an F[A => B], passing in an F[A].
// val f: Either[String, Int => Float] = Right(x => x + 1.0)
// f.ap(Right(1)) // Right(2.0)
extension [F[_]: Applicative, A, B](f: F[A => B])
  // Note the order of arguments here. We let F[A => B].ap(F[A]) be the
  // extension method, but in the Applicative trait, the F[A] argument comes
  // before the F[A => B] argument.
  def ap(fa: F[A]): F[B] = summon[Applicative[F]].ap(fa)(f)

// TODO Complete factorial, making sure that it is safe (use the Either type!)
// def factorial(n: Int) = ???
import scala.annotation.tailrec
def factorial(n: BigInt): BigInt = {
  @tailrec
  def _loop(acc: BigInt, n: BigInt): BigInt =
    if (n <= 1) acc else _loop(acc * n, n - 1)
  _loop(1, n)
}
// TODO Complete nChooseK, making sure that it is safe (use factorial!)
def nChooseK(n: Int, k: Int) = {
  val ans = factorial(n) / factorial(k)*factorial(n-k)
  println(ans)
}

// When you're ready, compile your code together with the test file:
// scala3-compiler *.scala
// Then, run the tests:
// scala3 test

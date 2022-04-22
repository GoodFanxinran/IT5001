// Amending this file will be of no use to you.

import scala.Console.{RED, GREEN, BLUE, CYAN, RESET}
import scala.util.{Try, Success}
@main def test: Unit =
  println(
    s"You scored ${eitherTest() + applicativeTest() + factorialTest() + nChooseKTest()} out of 75 marks for correctness for this assignment.\nThe remaining 25 marks will come from the design of your code."
  )
def eitherTest(): Int =
  var marks = 0
  println(s"$CYAN===== Either Test =====$RESET")
  println(
    s"${BLUE}Test 1: Left constructor accepts some item of type A and produces a Left[A].$RESET"
  )
  println(s"$BLUE>>>$RESET val l: Left[Int] = Left(1)")
  println(s"$BLUE>>>$RESET val l2: Left[String] = Left(\"Hello\")")
  val l: Left[Int] = Left(1)
  val l2: Left[String] = Left("Hello")
  println(s"${GREEN}Test 1 passed.$RESET\n")
  marks += 1

  println(
    s"${BLUE}Test 2: Right constructor accepts some item of type A and produces a Right[A].\n>>>$RESET val r: Right[Int] = Right(1)\n$BLUE>>>$RESET val r2: Right[String] = Right(\"Hello\")"
  )
  val r: Right[Int] = Right(1)
  val r2: Right[String] = Right("Hello")
  println(s"${GREEN}Test 2 passed.$RESET\n")
  marks += 1

  println(
    s"${BLUE}Test 3: Left[A] is a subtype of Either[A, B]\n>>>$RESET val e1: Either[String, Int] = Left(\"Hello\")\n$BLUE>>>$RESET val e2: Either[Object, String] = Left(Object())"
  )
  var e1: Either[String, Int] = Left("Hello")
  var e2: Either[Object, String] = Left(Object())
  println(s"${GREEN}Test 3 passed.\n$RESET")
  marks += 1

  println(
    s"${BLUE}Test 4: Right[B] is a subtype of Either[A, B]\n>>>$RESET val e1: Either[String, Int] = Right(1)\n$BLUE>>>$RESET val e2: Either[Object, String] = Right(\"Hello\")"
  )
  e1 = Right(1)
  e2 = Right("Hello")
  println(s"${GREEN}Test 4 passed.$RESET\n")
  marks += 1

  println(
    s"${BLUE}Test 5: Either[A, B] is matchable.\n>>>$RESET var e: Either[String, Int] = Left(\"Hello\")\n$BLUE>>>$RESET e match\n    |   case Left(x) => println(s\"Left $$x\")\n    |   case Right(x) => println(s\"Right $$x\")"
  )
  var totalCorrect = 0
  var e: Either[String, Int] = Left("Hello")
  val res1: String = e match
    case Left(x)  => s"Left $x"
    case Right(x) => s"Right $x"
  var correctness = isCorrect("Left Hello", res1)
  println(correctness._1)
  if correctness._2 then totalCorrect += 1
  println(
    s"$BLUE>>>$RESET e = Right(123)\n$BLUE>>>$RESET e match\n    |   case Left(x) => println(s\"Left $$x\")\n    |   case Right(x) => println(s\"Right $$x\")"
  )
  e = Right(123)
  val res2: String = e match
    case Left(x)  => s"Left $x"
    case Right(x) => s"Right $x"
  correctness = isCorrect("Right 123", res2)
  println(correctness._1)
  if correctness._2 then totalCorrect += 1
  if totalCorrect == 2 then println(s"${GREEN}Test 5 passed.$RESET")
  else println(s"${RED}Test 5 failed.$RESET")
  marks += totalCorrect
  printTotalMarks(marks * 3, 18)
  marks * 3
def applicativeTest() =
  var marks = 0
  println(s"$CYAN===== Applicative Test =====$RESET")
  println(s"${BLUE}Test 1: Any Applicative can be created$RESET")
  println(s"$BLUE>>>$RESET case class Box[+A](value: A)")
  given Applicative[Box] with
    def ap[A, B](fa: Box[A])(f: Box[A => B]): Box[B] =
      Box(f.value(fa.value))
  println(
    s"$BLUE>>>$RESET given Applicative[Box] with\n    |   def ap[A, B](fa: Box[A])(f: Box[A => B]): Box[B] =\n    |       Box(f.value(fa.value))"
  )
  println(s"$BLUE>>>$RESET Box[Int => Int](_ + 1).ap(Box(1))")
  println(s"${Box[Int => Int](_ + 1).ap(Box(1))} [${GREEN}correct$RESET]")
  println(s"${GREEN}Test 1 passed.$RESET\n")
  marks += 1
  var totalCorrect = 0

  println(s"${BLUE}Test 2: Either is a right-biased Applicative$RESET")
  println(
    s"$BLUE>>>$RESET val ef: Either[String, Int => String] = Right(x => (x + 1).toString)"
  )
  println(s"$BLUE>>>$RESET ef.ap(Right(4))")
  val ef: Either[String, Int => String] = Right(x => (x + 1).toString)
  var correct = isCorrect(Right("5"), ef.ap(Right(4)))
  println(correct._1)
  if correct._2 then totalCorrect += 1
  println(
    s"$BLUE>>>$RESET (Right(1):Either[String, Int]).map(x => (y: String) => x.toString + y)\n    |    .ap(Right(\"!\"))"
  )
  correct = isCorrect(
    Right("1!"),
    (Right(1): Either[String, Int])
      .map(x => (y: String) => x.toString + y)
      .ap(Right("!"))
  )
  println(correct._1)
  if correct._2 then totalCorrect += 1
  println(
    s"$BLUE>>>$RESET (Left(\"Error :(\"): Either[String, Int]).map(x => (y: String) => x.toString + y)\n    |   .ap(Right(\"!\"))"
  )
  correct = isCorrect(
    Left("Error :("),
    (Left("Error :("): Either[String, Int])
      .map(x => (y: String) => x.toString + y)
      .ap(Right("!"))
  )
  println(correct._1)
  if correct._2 then totalCorrect += 1

  marks += totalCorrect
  if totalCorrect == 3 then println(s"${GREEN}Test 2 passed.$RESET")
  else println(s"${RED}Test 2 failed.$RESET\n")
  printTotalMarks(marks * 3, 12)
  marks * 3

def factorialTest() =
  var marks = 0
  println(s"$CYAN===== Factorial Test =====$RESET")
  println(s"${BLUE}Test 1: Factorial returns some Either type$RESET.")
  println(
    s"$BLUE>>>$RESET factorial(1) match\n    |   case Left(_) => \"is an Either\"\n    |   case Right(_) => \"is an Either\"\n    |   case null => \"is not an Either\""
  )
  var correct = isCorrect(
    "is an Either",
    factorial(1) match
      case Left(_)  => "is an Either"
      case Right(_) => "is an Either"
      case null     => "is not an Either"
  )
  println(correct._1)
  if correct._2 then marks += 1
  if marks != 1 then println(s"${RED}Test 1 failed.$RESET")
  else println(s"${GREEN}Test 1 passed.$RESET")

  var totalCorrect = 0
  println(s"${BLUE}Test 2: Factorial works for small numbers.$RESET")

  println(s"$BLUE>>>$RESET factorial(0)")
  correct = isCorrect(Right(1), factorial(0).map(_.toInt))
  println(correct._1)
  if correct._2 then totalCorrect += 1

  println(s"$BLUE>>>$RESET factorial(-1)")
  correct = isCorrect(Left("Error!"), factorial(-1))
  println(correct._1)
  if correct._2 then totalCorrect += 1

  println(s"$BLUE>>>$RESET factorial(5)")
  correct = isCorrect(Right(120), factorial(5).map(_.toInt))
  println(correct._1)
  if correct._2 then totalCorrect += 1

  marks += totalCorrect
  if totalCorrect == 3 then println(s"${GREEN}Test 2 passed.$RESET")
  else println(s"${RED}Test 2 failed.$RESET")
  totalCorrect = 0

  println(s"${BLUE}Test 3: Factorial works for big numbers.$RESET")

  println(s"$BLUE>>>$RESET Try(factorial(-123456))")
  correct = isCorrect(Success(Left("Error!")), Try(factorial(-123456)))
  println(correct._1)
  if correct._2 then totalCorrect += 1

  println(
    s"$BLUE>>>$RESET Try(factorial(1000).map(_.toString).map(s => (s.substring(0, 20), s.length)))"
  )
  correct = isCorrect(
    Success(Right(("40238726007709377354", 2568))),
    Try(
      factorial(1000).map(_.toString).map(s => (s.substring(0, 20), s.length))
    )
  )
  println(correct._1)
  if correct._2 then totalCorrect += 1

  println(
    s"$BLUE>>>$RESET Try(factorial(100000).map(_.toString).map(s => (s.substring(0, 20), s.length)))"
  )
  correct = isCorrect(
    Success(Right(("28242294079603478742", 456574))),
    Try(
      factorial(100000).map(_.toString).map(s => (s.substring(0, 20), s.length))
    )
  )
  println(correct._1)
  if correct._2 then totalCorrect += 1

  marks += totalCorrect
  if totalCorrect == 3 then println(s"${GREEN}Test 3 passed.$RESET")
  else println(s"${RED}Test 3 failed.$RESET")
  totalCorrect = 0
  printTotalMarks(marks * 3, 21)
  marks * 3

def nChooseKTest() =
  var marks = 0
  println(s"$CYAN===== nChooseK Test =====$RESET")
  println(s"${BLUE}Test 1: nChooseK returns some Either type$RESET.")
  println(
    s"$BLUE>>>$RESET nChooseK(1, 1) match\n    |   case Left(_) => \"is an Either\"\n    |   case Right(_) => \"is an Either\"\n    |   case null => \"is not an Either\""
  )
  var correct = isCorrect(
    "is an Either",
    nChooseK(1, 1) match
      case Left(_)  => "is an Either"
      case Right(_) => "is an Either"
      case null     => "is not an Either"
  )
  println(correct._1)
  if correct._2 then marks += 1
  if marks != 1 then println(s"${RED}Test 1 failed.$RESET\n")
  else println(s"${GREEN}Test 1 passed.$RESET\n")

  println(s"${BLUE}Test 2: nChooseK works for small numbers.$RESET")
  var totalCorrect = 0
  println(s"$BLUE>>>$RESET nChooseK(0, 0)")
  correct = isCorrect(Right(1), nChooseK(0, 0).map(_.toInt))
  println(correct._1)
  if correct._2 then totalCorrect += 1

  println(s"$BLUE>>>$RESET nChooseK(0, 1)")
  correct = isCorrect(Left("Error!"), nChooseK(0, 1))
  println(correct._1)
  if correct._2 then totalCorrect += 1

  println(s"$BLUE>>>$RESET nChooseK(0, -1)")
  correct = isCorrect(Left("Error!"), nChooseK(0, -1).map(_.toInt))
  println(correct._1)
  if correct._2 then totalCorrect += 1
  println(s"$BLUE>>>$RESET nChooseK(10, 6)")
  correct = isCorrect(Right(210), nChooseK(10, 6).map(_.toInt))
  println(correct._1)
  if correct._2 then totalCorrect += 1

  marks += totalCorrect
  if totalCorrect == 4 then println(s"${GREEN}Test 2 passed.$RESET\n")
  else println(s"${RED}Test 2 failed.$RESET\n")
  totalCorrect = 0

  println(s"${BLUE}Test 3: nChooseK works for big numbers.$RESET")

  println(s"$BLUE>>>$RESET Try(nChooseK(-123456, -1234))")
  correct = isCorrect(Success(Left("Error!")), Try(nChooseK(-123456, -1234)))
  println(correct._1)
  if correct._2 then totalCorrect += 1

  println(
    s"$BLUE>>>$RESET Try(nChooseK(1000, 20).map(_.toString).map(s => (s.substring(0, 20), s.length)))"
  )
  correct = isCorrect(
    Success(Right(("33948281130245760389", 42))),
    Try(
      nChooseK(1000, 20)
        .map(_.toString)
        .map(s => (s.substring(0, 20), s.length))
    )
  )
  println(correct._1)
  if correct._2 then totalCorrect += 1

  println(
    s"$BLUE>>>$RESET Try(nChooseK(50000, 2000).map(_.toString).map(s => (s.substring(0, 20), s.length)))"
  )
  correct = isCorrect(
    Success(Right(("66080416325149842942", 3645))),
    Try(
      nChooseK(50000, 2000)
        .map(_.toString)
        .map(s => (s.substring(0, 20), s.length))
    )
  )
  println(correct._1)
  if correct._2 then totalCorrect += 1

  marks += totalCorrect
  if totalCorrect == 3 then println(s"${GREEN}Test 3 passed.\n$RESET")
  else println(s"${RED}Test 3 failed.\n$RESET")
  totalCorrect = 0
  printTotalMarks(marks * 3, 24)

  marks * 3

case class Box[+A](value: A)

def isCorrect[A](expected: A, output: A): (String, Boolean) =
  if output == expected then (s"${output} [${GREEN}correct$RESET]", true)
  else (s"${output} [${RED}wrong. correct answer: ${expected}$RESET]", false)

def printTotalMarks(marks: Int, total: Int) =
  val marksString =
    if marks < total then RED + marks.toString + RESET
    else GREEN + marks.toString + RESET
  println(
    s"You scored [${marksString}/$GREEN$total$RESET] marks for this test\n"
  )

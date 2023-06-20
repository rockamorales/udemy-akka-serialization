package part1_recap

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object ScalaRecap extends App {
  // declaring val amd functions
  val aCondition: Boolean = false

  def myFunction(x: Int) = {
    if (x > 4) 42 else 65
  }

  //instructions vs expressions
  // types + type inference

  // OO feature of scala
  class Animal
  trait Carnivore {
    def eat(a: Animal): Unit
  }

  //Companion object
  object Carnivore

  // Generics
  abstract class MyList[+A]

  // method notations
  1 + 2 // infix notation. Syntactic sugar for below
  1.+(2)

  // FP
  val anIncrementer: Int => Int = (x: Int) => x + 1
  anIncrementer(1)

  List(1,2,3).map(anIncrementer)
  //HOF - (Higher order functions): flatMap, filter
  // for-comprehensions, syntactic sugar for flatMap, filter chains

  // Monads: Option, Try

  // pattern matching

  val unknown: Any = 2
  val order = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }

  try {
    throw new RuntimeException
  } catch {
    case e: Exception => println("I caught one!")
  }

  /**
   * Scala advanced
   */

  // multithreading
  val future = Future {
    42
  }

  // Futures exposes map, flatMap, filter + other -- recover/recoverWith
  future.onComplete {
    case Success(value) => println(s"I found the meaning of life $value")
    case Failure(exception) => println(s"I found $exception while searching for the meaning of life")
  } // will be executed in some other thread

  val partialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 65
    case _ => 99
  }
  // partial function are based on pattern matching

  // type aliases
  type AkkaReceive = PartialFunction[Any, Unit]
  def receive: AkkaReceive = {
    case 1 => println("hello")
    case _ => println("confused...")
  }

  // Implicits
  implicit val timeout = 3000
  def setTimeout(f: () => Unit)(implicit timeout: Int) = f()

  setTimeout(() => println(timeout)) // other arg list injected by the compiler

  // implicits are often used for conversions
  // 1) implicit methods
  case class Person(name: String) {
    def greet: String = s"Hi, my nae is $name"
  }

  implicit def fromStringToPerson(name: String) = Person(name)

  "Peter".greet
  //fromStringToPerson("Peter").greet

  // 2)  implicit classes
  implicit class Dog(name: String) {
    def bark = println("Bark!")
  }
  "Lassie".bark
  // new Dog("Lassie").bark

  //implicit organizations
  // local scope
  implicit val numberOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  List(1,2,3).sorted // (numberOrdering) => List(3,2,1)

  // imported scope

  // Companion objects of the types involved in the call
  object Person {
    implicit val personOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  }

  List(Person("Bob"), Person("Alice")).sorted // Person.personOrdering will be injected as the second argument list


}

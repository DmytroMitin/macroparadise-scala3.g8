package $package$.core

import $package$.annotations.generateGreeting

@generateGreeting
final class Greeter

object Main:
  def main(args: Array[String]): Unit =
    val greeting: String = new Greeter().generatedGreeting
    assert(greeting == "Hello, Greeter!", greeting)
    println(greeting)

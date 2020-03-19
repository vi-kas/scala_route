package com.simple.maths

object SimpleMaths {

  def main(args: Array[String]): Unit = {
    println(s"This is a Simple Scala Project.")

    args
      .headOption
      .map(param => scala.util.Try(param.toInt))
      .map{param =>
        println(s"Since you passed a value, let me tell you it's ${if(isEven(param.get)) "even." else "odd."}")
      }
  }

  def isEven(number: Int): Boolean = number % 2 == 0
}
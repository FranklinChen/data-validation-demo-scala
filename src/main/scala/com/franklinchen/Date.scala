package com.franklinchen

import scalaz._
import Scalaz._

/**
  Dummy Date class for illustration only.
  */
case class Date(val seconds: Int) {
  def isBefore(date: Date): Boolean =
    seconds < date.seconds
}

object Date {
  sealed trait Error
  case class BadParse(s: String) extends Error {
    override def toString =
      s"failed to parse date string $s"
  }

  /**
    Try to parse the String s into a valid Date.
    */
  def parse(s: String): BadParse \/ Date = {
    try {
      Date(s.toInt).right
    } catch {
      case _: java.lang.NumberFormatException =>
        // Just for demo, ignore the exception.
        BadParse(s).left
    }
  }
}

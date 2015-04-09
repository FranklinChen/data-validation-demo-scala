package com.franklinchen

import scalaz._
import Scalaz._

case class BookingRequest private(
  val date: Date,
  val seats: Seats
)

object BookingRequest {
  /**
    Design pattern: collect all the errors this module handles.

    An alternate design: use an "open" error trait instead.

    Orthogonally: since Scala has exceptions anyway, could have
    Error extend Exception, to keep stack trace information.
    */
  sealed trait Error

  /** Wrapper. */
  case class DateError(e: Date.Error) extends Error {
    override def toString = e.toString
  }

  /** Wrapper. */
  case class SeatsError(e: Seats.Error) extends Error {
    override def toString = e.toString
  }

  case class DateBefore(date1: Date, date2: Date) extends Error {
    override def toString =
      s"date $date1 cannot be before $date2"
  }

  case class Missing(label: String) extends Error {
    override def toString =
      s"$label is missing"
  }

  def make(
    now: Date,
    optDateString: Option[String],
    optSeats: Option[Int]
  ): ValidationNel[Error, BookingRequest] = {
    val dateResult = makeTimelyBookingDate(now, optDateString)
    val seatsResult = makeSeats(optSeats)

    val combinedBuilder =
      dateResult.validation.toValidationNel |@|
      seatsResult.validation.toValidationNel

    combinedBuilder(BookingRequest(_, _))
  }

  def makeTimelyBookingDate(now: Date,
    optDateString: Option[String]): Error \/ Date = for {
    dateString <- optDateString.toRightDisjunction(Missing("date"))
    date <- Date.parse(dateString).leftMap(DateError)
    timelyDate <- timelyBookingDate(date, now)
  } yield timelyDate

  def makeSeats(optSeats: Option[Int]): Error \/ Seats = for {
    num <- optSeats.toRightDisjunction(Missing("seats"))
    validSeats <- Seats.make(num).leftMap(SeatsError)
  } yield validSeats

  def timelyBookingDate(date: Date, now: Date):
      DateBefore \/ Date = {
    if (!date.isBefore(now))
      date.right
    else
      DateBefore(date, now).left
  }
}

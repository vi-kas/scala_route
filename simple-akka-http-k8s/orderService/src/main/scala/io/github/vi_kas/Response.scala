package io.github.vi_kas

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute
import spray.json._
import io.github.vi_kas.data.Model._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

object Response {

  case class ErrorResponse(message: String, code: Int)

  case class ApiResponse[T](success: Boolean,
                            code: Option[Int] = None,
                            message: Option[T] = None)

  type ServiceResponse[T] = Either[ErrorResponse, T]

  def respond[A](response: Future[ServiceResponse[A]], successCode: StatusCode = StatusCodes.OK)
                (implicit ee: JsonWriter[ErrorResponse], rr: JsonWriter[A], jar: JsonWriter[ApiResponse[A]]): StandardRoute =

    complete {
      apiResponse(response)
    }

  private def apiResponse[A](response: Future[ServiceResponse[A]], successCode: StatusCode = StatusCodes.OK)
                            (implicit ee: JsonWriter[ErrorResponse], rr: JsonWriter[A], jar: JsonWriter[ApiResponse[A]]) =
    response map {
      case Left(error) => {
        (toStatusCode(error.code),
          ApiResponse(false, Some(error.code), Some(error.message)).toJson.toString)
      }
      case Right(value) => {
        (successCode,
          ApiResponse(true, None, Some(value)).toJson.toString)
      }
    }

  private def toStatusCode(i: Int): StatusCode =
    Try(StatusCode.int2StatusCode(i)).getOrElse(StatusCodes.InternalServerError)
}
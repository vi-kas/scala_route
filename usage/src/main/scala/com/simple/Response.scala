package com.simple

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

object Response {
  import io.circe._
  import io.circe.syntax._

  case class ErrorResponse(message: String, code: Int)

  case class ApiResponse(success: Boolean, code: Option[Int] = None,
                         message: Option[String] = None, data: Json = Json.fromString(""))

  type ServiceResponse[T] = Either[ErrorResponse, T]

  def respond[A](response: Future[ServiceResponse[A]])
                (implicit ee: Encoder[ApiResponse], rr: Encoder[A]): StandardRoute = complete(apiResponse(response))

  private def apiResponse[A](response: Future[ServiceResponse[A]])
                            (implicit ee: Encoder[ApiResponse], rr: Encoder[A]) =
    response map {
      case Left(error) => {
        (toStatusCode(error.code),
          ApiResponse(false, Some(error.code), Some(error.message)).asJson.toString)
      }
      case Right(value) => {
        (StatusCodes.Created,
          ApiResponse(true, data = value.asJson).asJson.toString)
      }
    }

  private def toStatusCode(i: Int): StatusCode =
    Try(StatusCode.int2StatusCode(i)).getOrElse(StatusCodes.InternalServerError)
}
package io.github.vi_kas.data

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import io.github.vi_kas.Response.{ApiResponse, ErrorResponse}
import spray.json._

object Model extends SprayJsonSupport with DefaultJsonProtocol {

  case class Order(id: UUID,
                   title: String,
                   price: Double,
                   quantity: Int)

  implicit object UUIDFormat extends JsonFormat[UUID] {
    def write(uuid: UUID) = JsString(uuid.toString)

    def read(value: JsValue) = {
      value match {
        case JsString(uuid) => UUID.fromString(uuid)
        case _ => throw new DeserializationException("Expected hexadecimal UUID string")
      }
    }
  }

  implicit val orderFormat = jsonFormat4(Order)
  implicit val errorFormat = jsonFormat2(ErrorResponse)
  implicit def apiResponseFormat[T: JsonFormat]: RootJsonFormat[ApiResponse[T]] = jsonFormat3(ApiResponse.apply[T])
}
package com.simple.libsupport

import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.model.{ContentType, ContentTypeRange, HttpEntity, MediaType}
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}
import akka.util.ByteString
import io.circe._

/**
  * https://doc.akka.io/docs/akka-http/current/common/marshalling.html
  */
trait CirceMarshallable {

  def unmarshallerContentTypes: Seq[ContentTypeRange] =
    mediaTypes.map(ContentTypeRange.apply)

  def mediaTypes: Seq[MediaType.WithFixedCharset] = List(`application/json`)

  implicit def jsonMarshaller(implicit printer: Printer): ToEntityMarshaller[Json] =
    Marshaller.oneOf(mediaTypes: _*){mediaType =>
      Marshaller.withFixedContentType(ContentType(mediaType)){json =>
        HttpEntity(mediaType,
          ByteString(printer.prettyByteBuffer(json, mediaType.charset.nioCharset())))
      }
    }

  implicit def marshaller[A: Encoder](implicit printer: Printer = Printer.noSpaces): ToEntityMarshaller[A] =
    jsonMarshaller(printer).compose(Encoder[A].apply)

  implicit final val jsonUnmarshaller: FromEntityUnmarshaller[Json] =
    Unmarshaller.byteStringUnmarshaller
      .forContentTypes(unmarshallerContentTypes: _*)
      .map {
        case ByteString.empty => throw Unmarshaller.NoContentException
        case data => jawn.parseByteBuffer(data.asByteBuffer).fold(throw _, identity)
      }

  implicit final def unmarshaller[A: Decoder]: FromEntityUnmarshaller[A] = {
    def decode(json: Json): A  = Decoder[A].decodeJson(json).fold(throw _, identity)
    jsonUnmarshaller.map(decode)
  }
}
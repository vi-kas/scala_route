package com.simple.data

import java.sql.Timestamp
import java.util.UUID

import io.circe.Decoder.Result
import io.circe._

object Model {

  implicit val TimestampJsonFormat : Encoder[Timestamp] with Decoder[Timestamp] =
    new Encoder[Timestamp] with Decoder[Timestamp] {
      override def apply(a: Timestamp): Json = Encoder.encodeLong.apply(a.getTime)
      override def apply(c: HCursor): Result[Timestamp] = Decoder.decodeLong.map(s => new Timestamp(s)).apply(c)
    }

  implicit val UUIDJsonFormat: Encoder[UUID] with Decoder[UUID] =
    new Encoder[UUID] with Decoder[UUID]{
      override def apply(a: UUID): Json = Encoder.encodeString.apply(a.toString)
      override def apply(c: HCursor): Result[UUID] = Decoder.decodeString.map(s => UUID.fromString(s)).apply(c)
    }

  trait Entity {
    def id: UUID
    def created: Timestamp
    def updated: Option[Timestamp]
  }

  case class User(id: UUID,
                  name: String,
                  email: Option[String],
                  created: Timestamp,
                  updated: Option[Timestamp]) extends Entity
}
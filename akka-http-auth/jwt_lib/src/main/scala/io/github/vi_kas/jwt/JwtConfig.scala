package io.github.vi_kas.jwt

import java.time.{Duration, Instant}
import java.util.Date

import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConverters._

case class JwtConfig(aud: Array[String], exp: Date, iss: String, iat: Date = new Date(), sub: String, claims: Map[String, String] = Map.empty)

object JwtConfig {

  def fromConfig(config: Config = ConfigFactory.load()): JwtConfig = {
    val jwtConfig = config.getConfig("jwt")
    val instant = Instant.now

    new JwtConfig(
      aud = jwtConfig.getStringList("aud").asScala.toArray,
      sub = jwtConfig.getString("sub"),
      iss = jwtConfig.getString("iss"),
      iat = Date.from(instant),
      exp = Date.from(instant.plus(Duration.ofDays(1)))
    )
  }
}
package io.github.vi_kas.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.{Claim, DecodedJWT}
import com.typesafe.config.{Config, ConfigFactory}

import scala.util.{Failure, Success, Try}

object JwtUtil {

  val config: Config = ConfigFactory.load()
  // private val logger: Logger = LoggerFactory.getLogger(getClass)

  def create(jwtConfig: JwtConfig, algo: Algorithm): Try[String] = {

    Try {
      val tokenBuilder = JWT
        .create()
        .withIssuer(jwtConfig.iss)
        .withIssuedAt(jwtConfig.iat)
        .withExpiresAt(jwtConfig.exp)
        .withSubject(jwtConfig.sub)
        .withArrayClaim("aud", jwtConfig.aud)

      jwtConfig
        .claims
        .foldLeft(tokenBuilder)((a, v) => {
          a.withClaim(v._1, v._2)
        })
        .sign(algo)
    }
  }

  def rsaAlgorithm: Try[Algorithm] = {
    import KeyUtil._

    for {
      privK <- readRSAPrivateKeyFromFile("./resourceServer/src/main/resources/keystore/private_key.pem")
      pubK <- readRSAPublicKeyFromFile("./resourceServer/src/main/resources/keystore/public_key.pem")
    } yield Algorithm.RSA256(pubK, privK)
  }

  def hmacAlgorithm: Try[Algorithm] = Try(Algorithm.HMAC256("0az91by8cx2dw73ev6fu46g5"))

  def getJWT(token: String): Option[DecodedJWT] =
    Try {
      JWT.decode(token)
    } match {
      case Failure(_) =>
        // logger.error(s"Couldn't get the claim: Exception: ${exception.getMessage}")
        None
      case Success(jwt) =>
        Some(jwt)
    }

  def getClaim(claim: String, token: String): Option[Claim] =
    Try {
      val jwt = JWT.decode(token)
      val result: Claim = jwt.getClaim(claim)
      if(result.isNull) None else Some(result)
    } match {
      case Failure(exception) =>
        // logger.error(s"Couldn't get the claim: Exception: ${exception.getMessage}")
        None
      case Success(claimValue) =>
        claimValue
    }
}
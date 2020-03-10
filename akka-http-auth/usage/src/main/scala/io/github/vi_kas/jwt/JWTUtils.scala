package io.github.vi_kas.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

import scala.util.Try

object JWTUtils {

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
    import RSAKeyUtils._

    readRSAPrivateKeyFromFile("./resourceServer/src/main/resources/keystore/private_key.pem")
      .flatMap(privK =>
        readRSAPublicKeyFromFile("./resourceServer/src/main/resources/keystore/public_key.pem")
          .map(pubK => Algorithm.RSA256(pubK, privK)))
  }

  def hmacAlgorithm: Try[Algorithm] = Try(Algorithm.HMAC256("0az91by8cx2dw73ev6fu46g5"))

}
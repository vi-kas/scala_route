package io.github.vi_kas.jwt

import java.security.interfaces.{RSAPrivateKey, RSAPublicKey}

import com.auth0.jwk.{Jwk, UrlJwkProvider}

import scala.util.Try

//https://connect2id.com/products/nimbus-jose-jwt/examples/jwk-conversion
object RSAKeyUtils {
  import PemUtils._

  def readRSAPublicKeyFromFile(pemFile: String): Try[RSAPublicKey] =
    getRSAPublicKey(pemFile)

  def readRSAPrivateKeyFromFile(pemFile: String): Try[RSAPrivateKey] =
    getRSAPrivateKey(pemFile)

  def readFromJWKString(jwkString: String): Option[RSAPublicKey] = None

  def readJwkFromURL(url: String, kid: String): Try[Jwk] = {
    val provider = new UrlJwkProvider(url)

    Try(provider.get(kid))
  }
}
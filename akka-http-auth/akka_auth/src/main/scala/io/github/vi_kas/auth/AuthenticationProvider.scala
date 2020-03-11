package io.github.vi_kas.auth

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{AuthorizationFailedRejection, Directive1}
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

trait AuthenticationProvider {

  def authenticate: Directive1[Option[AuthenticationResult]]
}

class AuthenticationProviderDefaultImpl(
                                         config: Config = ConfigFactory.load(),
                                         jwtVerifier: JwtVerifier = new JwtVerifierDefaultImpl) extends AuthenticationProvider {

  private val JWT_COOKIE_NAME = "_sessiondata" // config.getString("akka_auth.jwt.auth_cookie_name")
  private val JWT_HEADER_NAME = "SESSION_DATA" // config.getString("akka_auth.jwt.auth_header_name")

  def extractCookie: Directive1[Option[String]] = optionalCookie(JWT_COOKIE_NAME).map(_.map(_.value))

  def extractHeader: Directive1[Option[String]] = optionalHeaderValueByName(JWT_HEADER_NAME)

  def extractToken: Directive1[Option[String]] =
    extractCookie
      .flatMap {
        case None => extractHeader
        case t @ Some(_) => provide(t)
      }

  def extractAuthenticatedInstance(token: String): Directive1[Option[AuthenticationResult]] =
    extractDecodedJwt(token).flatMap {
      case decoded @ Some(_) =>
        provide(decoded.map(t => AuthenticationResult(t.getSubject, t.getClaims.asScala.toMap)))
      case None => reject(AuthorizationFailedRejection)
    }

  def extractDecodedJwt(token: String): Directive1[Option[DecodedJWT]] =
    Try(JWT.decode(token)) match {
      case Success(decodedJWT) => provide(Some(decodedJWT))
      case Failure(_) => reject(AuthorizationFailedRejection)
    }

  def extractClaims(token: String): Directive1[Set[String]] = provide(Set.empty[String])

  def extractRoles(token: String): Directive1[Set[String]] = provide(Set.empty[String])

  def authenticate: Directive1[Option[AuthenticationResult]] =
    extractToken
      .flatMap(jwtVerifier.validateToken)
      .flatMap(extractAuthenticatedInstance)

}
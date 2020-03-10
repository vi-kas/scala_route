package io.github.vi_kas.auth

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.{Claim, DecodedJWT}
import io.github.vi_kas.auth.AuthDirective.Authenticated

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

object AuthDirective {

  case class Authenticated(subject: String, claims: Map[String, Claim] = Map.empty)

  def secured(authenticator: AuthenticationProvider): Directive1[Authenticated] =
    authenticator.authenticate.flatMap {
      case Some(value) => provide(value)
      case None => reject(AuthenticationFailedRejection(""))
    }

  //Policy to authorize/reject
  def authorized(authenticated: Authenticated): Directive0 =
    authenticated.subject match {
      case "Straight Six" => pass
      case "One Bounce Four" => reject(AuthorizationFailed("One Bounce Four ain't a Six!"))
    }

  case class AuthenticationFailedRejection(cause: String) extends Throwable with Rejection
}

class AuthenticationProvider(jwtVerifier: JwtVerifier) {

  val JWT_COOKIE_NAME = "_sessiondata"
  val JWT_HEADER_NAME = "SESSION_DATA"

  def extractCookie: Directive1[Option[String]] = optionalCookie(JWT_COOKIE_NAME).map(_.map(_.value))

  def extractHeader: Directive1[Option[String]] = optionalHeaderValueByName(JWT_HEADER_NAME)

  def extractToken: Directive1[Option[String]] =
    extractCookie.flatMap {
      case None => extractHeader
      case t @ Some(_) => provide(t)
    }

  def extractAuthenticatedInstance(token: String): Directive1[Option[Authenticated]] =
    extractDecodedJwt(token).flatMap {
      case decoded @ Some(_) =>
        provide(decoded.map(t => Authenticated(t.getSubject, t.getClaims.asScala.toMap)))
      case None => reject(AuthorizationFailedRejection)
    }

  def extractDecodedJwt(token: String): Directive1[Option[DecodedJWT]] =
    Try(JWT.decode(token)) match {
      case Success(decodedJWT) => provide(Some(decodedJWT))
      case Failure(_) => reject(AuthorizationFailedRejection)
    }

  def extractClaims(token: String): Directive1[Set[String]] = provide(Set.empty[String])

  def extractRoles(token: String): Directive1[Set[String]] = provide(Set.empty[String])

  def authenticate: Directive1[Option[Authenticated]] =
    extractToken
      .flatMap(jwtVerifier.validateToken)
      .flatMap(extractAuthenticatedInstance)
}

sealed trait AuthorizationResult
case object AuthorizationSuccess extends AuthorizationResult
case class AuthorizationFailed(cause: String) extends AuthorizationResult with Rejection

object AuthImpl {

  val authenticationProviderImpl: AuthenticationProvider = new AuthenticationProvider(JwtVerifier())
}

class JwtVerifier {

  def validate(token: String): Directive0 = pass

  def validateToken(token: Option[String]): Directive1[String] =
    token match {
      case None => reject(AuthorizationFailedRejection)
      case Some(t) => validate(t) & provide(token.get)
    }
}

object JwtVerifier {

  def apply(): JwtVerifier = new JwtVerifier()
}
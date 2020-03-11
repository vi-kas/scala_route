package io.github.vi_kas.auth

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{AuthorizationFailedRejection, Directive0, Directive1}

trait JwtVerifier {

  def validate(token: String): Directive0

  def validateToken(token: Option[String]): Directive1[String]
}

class JwtVerifierDefaultImpl extends JwtVerifier {

  override def validate(token: String): Directive0 = pass

  override def validateToken(token: Option[String]): Directive1[String] =
    token match {
      case None => reject(AuthorizationFailedRejection)
      case Some(t) => validate(t) & provide(token.get)
    }

}
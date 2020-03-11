package io.github.vi_kas.auth

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{AuthorizationFailedRejection, Directive1}

object AuthenticationDirective {

  def secured(authenticator: AuthenticationProvider = new AuthenticationProviderDefaultImpl): Directive1[AuthenticationResult] =
    authenticator
      .authenticate
      .flatMap {
        case Some(value) => provide(value)
        case None => reject(AuthorizationFailedRejection)
      }
}

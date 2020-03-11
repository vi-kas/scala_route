package io.github.vi_kas.auth

import com.auth0.jwt.interfaces.Claim

case class AuthenticationResult(subject: String, claims: Map[String, Claim] = Map.empty[String, Claim])
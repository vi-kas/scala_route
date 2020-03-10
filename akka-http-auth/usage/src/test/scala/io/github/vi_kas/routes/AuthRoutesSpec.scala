package io.github.vi_kas.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, pathEndOrSingleSlash, pathPrefix, post, _}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.github.vi_kas.auth.AuthDirective._
import io.github.vi_kas.auth.AuthImpl
import io.github.vi_kas.jwt.{JWTUtils, JwtConfig}
import org.scalatest.{Matchers, WordSpec}

import scala.util.Try

class AuthRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest {

  val dummyRoutes: Route =
    pathPrefix("secured"){

      pathEndOrSingleSlash {
        (post & entity(as[String])) { e =>
          secured(AuthImpl.authenticationProviderImpl){ x =>
            println(s"[secured req] - x: $x")

            authorized(x){
              complete(StatusCodes.Created, e)
            }
          }
        } ~ get {
          complete(StatusCodes.OK)
        }
      }
    }

  "return a Unauthorized status for POST to /secured without token" in {

    Post("/secured/") ~> dummyRoutes ~> check {
      assert(response.status == StatusCodes.Unauthorized)
    }
  }

  "return a CREATED status for POST to /secured" in {
    val config = JwtConfig.fromConfig(ConfigFactory.load("application.conf"))

    val hmacAlgo: Try[Algorithm] = JWTUtils.hmacAlgorithm
    assert(hmacAlgo.isSuccess)

    val token = JWTUtils.create(config.copy(sub = "Straight Six"), hmacAlgo.get)
    println(token)
    val header = RawHeader("SESSION_DATA", token.get)

    Post("/secured/").addHeader(header) ~> dummyRoutes ~> check {
      assert(response.status == StatusCodes.Created)
    }
  }

}
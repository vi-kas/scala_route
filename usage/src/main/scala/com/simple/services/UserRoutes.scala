package com.simple.services

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.simple.Response._
import com.simple.Routes
import com.simple.data.Model._
import com.simple.data._
import io.circe.generic.auto._

trait UserRoutes extends Routes with UserDBComponent {
  self: UserServiceComponent =>

  val userRoutes: Route =
    pathPrefix("users") {
      get {
        respond(userService.getAllUsers)
      } ~
        (post & entity(as[User])) { user =>
          complete(StatusCodes.Created)
        }
    }
}
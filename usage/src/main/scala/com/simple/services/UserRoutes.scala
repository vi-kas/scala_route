package com.simple.services

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.simple.Response._
import com.simple.Routes
import com.simple.data.Model._
import com.simple.data._
import io.circe.generic.auto._


/*
* [#] GET       /api/v1/users
* [#] GET       /api/v1/users/10000000-0000-0000-0000-000000000000
* [ ] PUT       /api/v1/users/10000000-0000-0000-0000-000000000000
* [#] Delete    /api/v1/users/10000000-0000-0000-0000-000000000000
* [#] Delete    /api/v1/users/10000000-0000-0000-0000-000000000000
* */
trait UserRoutes extends Routes with UserDBComponent {
  self: UserServiceComponent =>

  val userRoutes: Route =
    pathPrefix("api" / "v1" / "users") {

      get {
        respond(userService.getAllUsers)
      } ~
        pathEndOrSingleSlash {

          (post & entity(as[User])) { user =>
            respond(userService.createUser(user)) //ToDo Make it Work
          }
        } ~
        path(JavaUUID) {id =>

          get {
            respond(userService.getUserById(id))
          } ~
            (put & entity(as[User])){user =>
              complete(StatusCodes.Accepted)
            } ~
            delete {
              respond(userService.deleteUser(id))
            }
        }
    }

}
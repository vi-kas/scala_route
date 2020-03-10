package io.github.vi_kas.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import com.typesafe.config.{Config, ConfigFactory}
import io.github.vi_kas.Response._
import io.github.vi_kas.data.Model._
import io.github.vi_kas.services.OrderService

class OrderRoutes(orderService: OrderService) {

  def config: Config = ConfigFactory.load()

  trait ApiV1 {
    val apiPrefix: Directive[Unit] = pathPrefix("api" / "v1")
  }

  val routes: Route =
    pathPrefix("orders") {

      pathEndOrSingleSlash {

        //POST /orders
        (post & entity(as[Order])) { order =>
          respond(orderService.create(order))
        } ~
          get {
            //GET /orders
            respond(orderService.all)
          }
      } ~
        {
          path(JavaUUID) {id =>

            pathEndOrSingleSlash {

              //GET /orders/someUUID
              get {
                respond(orderService.byId(id))
              } ~
                (put & entity(as[Order])){ _ =>     //PUT /orders/someUUID

                  complete(StatusCodes.Accepted)
                } ~
                delete {
                  //DELETE /orders/someUUID
                  respond(orderService.delete(id))
                }
            }
          }
        }

    }

}
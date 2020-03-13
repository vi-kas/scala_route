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

  /* Akka Http Routes
   * ----------------
   * Use of `concat` here:
   * Tries the supplied routes in sequence, returning the result of the first route that doesn't reject the request.
   *
   * Eg.
   * In the case of:
   *    GET /orders - `get(complete(StatusCodes.OK, s"GET All Orders request."))` will be executed.
   *
   *    GET /orders/<some uuid> - `get(complete(StatusCodes.OK, s"GET request for id: $id"))` will be executed.
   *    GET /orders/<uuid>/price - `(get & path("price"))(complete(StatusCodes.OK, s"Price: DummyPriceValue for id: $id"))`
   *
   *    GET /orders/count - `(get & path("count"))(complete(StatusCodes.OK, "Count: DummyCountNumber"))` will be executed.
   */
  val routes: Route =
    pathPrefix("orders"){
      concat(
        pathEndOrSingleSlash {
          concat(
            get(respond(orderService.all)),
            (post & entity(as[Order]))(order => respond(orderService.create(order)))
          )
        },
        pathPrefix(JavaUUID){ id =>
          concat(
            pathEndOrSingleSlash {
              concat(
                get(respond(orderService.byId(id))),
                delete(respond(orderService.delete(id))),
                (put & entity(as[Order]))(_ => complete(StatusCodes.Accepted))
              )
            },
            (get & path("price"))(complete(StatusCodes.OK, "Price: DummyPriceValue"))
          )
        },
        (get & path("count"))(complete(StatusCodes.OK, "Count: DummyCountNumber"))
      )
    }

}
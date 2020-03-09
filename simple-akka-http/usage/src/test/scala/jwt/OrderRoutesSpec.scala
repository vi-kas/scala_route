package jwt

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import io.github.vi_kas.routes.OrderRoutes
import io.github.vi_kas.services.OrderServiceImpl
import org.scalatest.{Matchers, WordSpec}

class OrderRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest {

  val orderService = new OrderServiceImpl
  val orderServiceRoutes = new OrderRoutes(orderService)

  "Order service" should {

    "return a success for GET to /orders" in {
      Get("/orders") ~> orderServiceRoutes.routes ~> check {
        assert(response.status == StatusCodes.OK)
      }
    }
  }

}

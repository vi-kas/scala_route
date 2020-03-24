package io.github.vi_kas

import java.util.UUID

import io.github.vi_kas.config.OrderCacheConfiguration
import io.github.vi_kas.models.Order
import org.apache.ignite.IgniteCache
import org.apache.ignite.configuration.{CacheConfiguration, IgniteConfiguration}
import org.slf4j.{Logger, LoggerFactory}

object IgniteApp {

  /*
   *  Application entry point.
   */
  def main(args: Array[String]): Unit = {

    val logger: Logger = LoggerFactory.getLogger(getClass)

    val orderCacheConfiguration: CacheConfiguration[UUID, Order] = OrderCacheConfiguration("ignite_order_t")

    val someOrderUUID: UUID = UUID.fromString("323b68ec-1cb8-4110-c214-f75079b15719")
    val someOrder: Order = Order(someOrderUUID, "IPhone 11 Pro", 1, 1699)

    val igniteConfiguration: IgniteConfiguration =
      new IgniteConfiguration()
        .setCacheConfiguration(orderCacheConfiguration)

    val orderPGCacheInstance: IgniteCache[UUID, Order] =
      IgniteCacheServer(igniteConfiguration).start(orderCacheConfiguration)

    logger.info(s"Putting Order info: $someOrder into Cache!")
    orderPGCacheInstance.put(someOrderUUID, someOrder)

    val order: Order = orderPGCacheInstance.get(someOrderUUID)
    logger.info(s"Order retrieved from Cache: $order")

    logger.info(s"Remove Order with ID: $someOrderUUID")
    orderPGCacheInstance.remove(someOrderUUID)
  }
}
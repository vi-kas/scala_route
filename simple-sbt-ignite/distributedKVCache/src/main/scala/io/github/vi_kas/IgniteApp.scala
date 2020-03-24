package io.github.vi_kas

import java.util.UUID

import io.github.vi_kas.models.Order
import org.apache.ignite.IgniteCache
import org.apache.ignite.client.ClientCache
import org.apache.ignite.configuration.{CacheConfiguration, ClientConfiguration, ClientConnectorConfiguration, IgniteConfiguration}
import org.slf4j.{Logger, LoggerFactory}

object IgniteApp {

  /*
   *  Application entry point.
   */
  def main(args: Array[String]): Unit = {

    val logger: Logger = LoggerFactory.getLogger(getClass)
    val orderCacheConfiguration: CacheConfiguration[UUID, Order] = Order.cacheConfiguration

    val someOrderUUID = UUID.fromString("323b68ec-1cb8-4110-c214-f75079b15719")
    val someOrder = Order(someOrderUUID, "IPhone 11 Pro", 1, 1699)

    /*
     *  Configuration for clients to connect.
     */
    val clientConnectorConfiguration: ClientConnectorConfiguration =
      new ClientConnectorConfiguration()
        .setHost("127.0.0.1")
        .setPort(10800)

    /*
     *  Ignite Grid runtime configuration
     */
    val igniteConfiguration: IgniteConfiguration =
      new IgniteConfiguration()
        .setIgniteInstanceName("simple-ignite-instance")
        .setClientConnectorConfiguration(clientConnectorConfiguration)
        .setClientMode(false)

    /*
     *  Starts Ignite Grid with given igniteConfiguration.
     *  and Gets an IgniteCache with given cacheConfiguration.
     */
    val orderCacheInstance: IgniteCache[UUID, Order] =
      IgniteCacheServer(igniteConfiguration).start(orderCacheConfiguration)

    logger.info(s"Putting Order info: $someOrder into Cache!")
    orderCacheInstance.put(someOrderUUID, someOrder)


    // ----------- Client Specific --------------------------------------------------- //

    val clientConfiguration: ClientConfiguration =
      new ClientConfiguration()
        .setAddresses("127.0.0.1:10800")

    val cacheClient: Option[ClientCache[UUID, Order]] =
      IgniteCacheClient(clientConfiguration).initCache[UUID, Order](orderCacheConfiguration.getName)

    val orderFromCache: Option[Order] = cacheClient.map(_.get(someOrderUUID))
    orderFromCache match {
      case Some(order) => logger.info(s"Order retrieved from Cache: $order")
      case None => logger.info(s"Couldn't retrieve order $someOrderUUID from cache.")
    }


    //Closing Ignite Cache Instance
    orderCacheInstance.close()
  }
}
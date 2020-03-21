package io.github.vi_kas

import java.util.UUID

import io.github.vi_kas.model.Order
import org.apache.ignite.client.ClientCache
import org.apache.ignite.configuration.ClientConfiguration
import org.apache.ignite.{Ignition, client}

import scala.util.Try

class IgniteCacheClient private (clientConfiguration: ClientConfiguration) {

  def initCache[K, V](name: String): ClientCache[K, V] = {
    println(s"[IgniteCacheClient] - Initiating cache: $name.")

    Try {
      val ignite: client.IgniteClient =
        Ignition.startClient(clientConfiguration)

      val clientCache: ClientCache[K, V] = ignite.cache(name)
      clientCache
    }.get
  }
}

object IgniteCacheClient extends App {
  println(s"[IgniteCacheClient] - Starting.")

  val clientConf = new ClientConfiguration().setAddresses("127.0.0.1:10800")
  println(s"[IgniteCacheClient] - Starting with Client Configuration: $clientConf")

  val igniteCacheClient = new IgniteCacheClient(clientConf)
  val clientCache = igniteCacheClient.initCache[UUID, Order]("order")

  val uuid = UUID.fromString("323b68ec-1cb8-4110-c214-f75079b15719")

  val res: Order = clientCache.get(uuid)
  println(s"RESULT: $res")
}
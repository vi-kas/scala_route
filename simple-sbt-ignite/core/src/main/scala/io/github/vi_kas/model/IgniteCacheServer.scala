package io.github.vi_kas.model

import java.util.UUID

import com.typesafe.config.ConfigFactory
import org.apache.ignite.configuration.{CacheConfiguration, ClientConnectorConfiguration, IgniteConfiguration}
import org.apache.ignite.{Ignite, IgniteCache, Ignition}

class IgniteCacheServer private(config: IgniteConfiguration) {

  def start[K,V](cacheConfig: CacheConfiguration[K,V]): IgniteCache[K, V] = {
    println(s"[IgniteCacheServer] - Starting Ignite Instance with CacheConfig: $cacheConfig.")

    val ignite = Ignition.start(config)
    ignite.getOrCreateCache(cacheConfig)
  }

  def stop(igniteInstance: Ignite): Unit = {
    println(s"[IgniteCacheServer] - Stopping Ignite Instance.")

    igniteInstance.close()
  }
}

object IgniteCacheServer extends App {
  println(s"[IgniteCacheServer] - Starting IgniteCacheServer.")

  val appConfig = ConfigFactory.load()

  val connectorConfig =
    new ClientConnectorConfiguration()
      .setHost("127.0.0.1")
      .setPort(10800)

  val igniteConfig =
    new IgniteConfiguration()
      .setIgniteInstanceName("simple-ignite-instance")
      .setClientConnectorConfiguration(connectorConfig)
      .setClientMode(false)

  val igniteCacheServer = new IgniteCacheServer(igniteConfig)

  val cacheConfigurationToLoad = Order.cacheConfiguration

  val igniteCache =  igniteCacheServer
    .start(cacheConfigurationToLoad)

  val uuid = UUID.fromString("323b68ec-1cb8-4110-c214-f75079b15719")

  igniteCache.put(uuid, Order(uuid, "IPhone 11 Pro", 1, 1699))
}

package io.github.vi_kas

import org.apache.ignite.client.ClientCache
import org.apache.ignite.configuration.ClientConfiguration
import org.apache.ignite.{Ignition, client}

import scala.util.Try

class IgniteCacheClient private (clientConfiguration: ClientConfiguration) {

  def initCache[K, V](name: String): Option[ClientCache[K, V]] = {
    println(s"[IgniteCacheClient] - Initiating cache: $name.")

    Try {
      val ignite: client.IgniteClient =
        Ignition.startClient(clientConfiguration)

      val clientCache: ClientCache[K, V] = ignite.cache(name)
      clientCache
    }.toOption
  }
}

object IgniteCacheClient{
  println(s"[IgniteCacheClient] - Starting.")

  def apply(clientConfiguration: ClientConfiguration): IgniteCacheClient =
    new IgniteCacheClient(clientConfiguration)
}
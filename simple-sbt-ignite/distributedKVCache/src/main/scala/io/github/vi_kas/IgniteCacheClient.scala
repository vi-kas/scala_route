package io.github.vi_kas

import org.apache.ignite.client.ClientCache
import org.apache.ignite.configuration.ClientConfiguration
import org.apache.ignite.{Ignition, client}
import org.slf4j.{Logger, LoggerFactory}

import scala.util.Try

class IgniteCacheClient private (clientConfiguration: ClientConfiguration) {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  def initCache[K, V](name: String): Option[ClientCache[K, V]] = {
    logger.info(s"[IgniteCacheClient] - Initiating cache: $name.")

    Try {
      val ignite: client.IgniteClient =
        Ignition.startClient(clientConfiguration)

      val clientCache: ClientCache[K, V] = ignite.cache(name)
      clientCache
    }.toOption
  }
}

object IgniteCacheClient{

  def apply(clientConfiguration: ClientConfiguration): IgniteCacheClient =
    new IgniteCacheClient(clientConfiguration)
}
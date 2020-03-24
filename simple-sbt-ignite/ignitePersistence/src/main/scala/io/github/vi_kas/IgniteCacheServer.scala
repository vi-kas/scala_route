package io.github.vi_kas

import org.apache.ignite.configuration.{CacheConfiguration, IgniteConfiguration}
import org.apache.ignite.{Ignite, IgniteCache, Ignition}
import org.slf4j.{Logger, LoggerFactory}

class IgniteCacheServer private(config: IgniteConfiguration) {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  def start[K,V](cacheConfig: CacheConfiguration[K,V]): IgniteCache[K, V] = {
    logger.info(s"[IgniteCacheServer] - Starting Ignite Instance with CacheConfig: $cacheConfig.")

    val ignite = Ignition.start(config)
    ignite.getOrCreateCache(cacheConfig)
  }

  def stop(igniteInstance: Ignite): Unit = {
    logger.info(s"[IgniteCacheServer] - Stopping Ignite Instance.")

    igniteInstance.close()
  }
}

object IgniteCacheServer {

  def apply(config: IgniteConfiguration): IgniteCacheServer =
    new IgniteCacheServer(config)
}
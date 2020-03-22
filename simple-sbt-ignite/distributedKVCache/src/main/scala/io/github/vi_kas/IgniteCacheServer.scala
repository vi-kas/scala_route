package io.github.vi_kas

import org.apache.ignite.configuration.{CacheConfiguration, IgniteConfiguration}
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

object IgniteCacheServer {
  println(s"[IgniteCacheServer] - Starting IgniteCacheServer.")

  def apply(config: IgniteConfiguration): IgniteCacheServer =
    new IgniteCacheServer(config)
}
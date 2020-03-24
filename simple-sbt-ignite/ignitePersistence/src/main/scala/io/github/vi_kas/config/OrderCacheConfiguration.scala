package io.github.vi_kas.config

import java.util.UUID

import io.github.vi_kas.IgnitePostgresStore
import io.github.vi_kas.models.Order
import javax.cache.configuration.FactoryBuilder
import org.apache.ignite.cache.CacheMode
import org.apache.ignite.configuration.CacheConfiguration
import org.slf4j.{Logger, LoggerFactory}

object OrderCacheConfiguration {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  def apply(name: String): CacheConfiguration[UUID, Order] = {
    logger.info(s"[OrderCacheConfiguration] - Creating OrderCache with name: $name")

    new CacheConfiguration[UUID, Order](name)
      .setCacheStoreFactory(FactoryBuilder.factoryOf(classOf[IgnitePostgresStore]))
      .setBackups(1)
      .setCacheMode(CacheMode.REPLICATED)
      .setReadThrough(true)
      .setWriteThrough(true)
  }
}
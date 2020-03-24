package io.github.vi_kas.models

import java.util
import java.util.UUID

import org.apache.ignite.cache.{CacheAtomicityMode, QueryEntity, QueryIndex}
import org.apache.ignite.configuration.CacheConfiguration
import org.slf4j.{Logger, LoggerFactory}

case class Order(id: UUID, title: String, quantity: Int, price: Double)

object Order {
  val logger: Logger = LoggerFactory.getLogger(getClass)

  val queryEntities: java.util.Collection[QueryEntity] = new util.ArrayList[QueryEntity]()
  val queryIndexes: java.util.Collection[QueryIndex] = new util.ArrayList[QueryIndex]()

  queryIndexes.add(new QueryIndex("id"))

  queryEntities.add(
    new QueryEntity(classOf[UUID], classOf[Order])
      .addQueryField("id", classOf[UUID].getName, null)
      .addQueryField("title", classOf[String].getName, null)
      .addQueryField("quantity", classOf[Int].getName, null)
      .addQueryField("price", classOf[Double].getName, null)
      .setIndexes(queryIndexes)
  )

  def cacheConfiguration: CacheConfiguration[UUID, Order] = {
    logger.info(s"[Order] - Creating QueryEntities - $queryEntities with QueryIndexes: $queryIndexes.")

    new CacheConfiguration[UUID, Order]()
      .setName("order")
      .setTypes(classOf[UUID], classOf[Order])
      .setQueryEntities(queryEntities)
      .setAtomicityMode(CacheAtomicityMode.ATOMIC)
  }

}

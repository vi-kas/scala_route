package io.github.vi_kas

import java.util.UUID

import io.github.vi_kas.models.Order
import javax.cache.configuration.FactoryBuilder
import org.apache.ignite.cache.CacheMode
import org.apache.ignite.configuration.{CacheConfiguration, IgniteConfiguration}
import org.apache.ignite.lang.IgniteFuture
import org.apache.ignite.{IgniteCache, Ignition}

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success, Try}

object IgniteApp {

  /*
   *  Application entry point.
   */
  def main(args: Array[String]): Unit = {

    implicit val ec = scala.concurrent.ExecutionContext.global

    val config = new IgniteConfiguration()
    val cacheCfg = new CacheConfiguration[String, Order]("ignite_order_t")

    cacheCfg.setCacheStoreFactory(FactoryBuilder.factoryOf(classOf[IgnitePostgresStore]))
    cacheCfg.setBackups(1)
    cacheCfg.setCacheMode(CacheMode.REPLICATED)
    cacheCfg.setReadThrough(true)
    cacheCfg.setWriteThrough(true)

    config.setCacheConfiguration(cacheCfg)

    val ignition = Ignition.start(config)
    val jdbcCache: IgniteCache[String, Order] =
      ignition.getOrCreateCache[String, Order]("ignite_order_t")

    for (i <- 1 to 10) {
      jdbcCache.put(i.toString, Order(UUID.randomUUID(), s"order-$i", i + 1, i * 20.0 ))
    }

    for (i <- 1 to 10) {
      println(jdbcCache.get(i.toString))
    }

    implicit class IgniteFutureUtils[T](igniteFuture: IgniteFuture[T]) {
      def toScalaFuture: Future[T] = {
        val promise = Promise[T]()

        igniteFuture.listen { k =>
          promise.tryComplete(Try(k.get))
        }
        promise.future
      }
    }

    jdbcCache.removeAsync("1").toScalaFuture onComplete {
      case Success(removed) => println(s"Removed = $removed")
      case Failure(f) => println("Remove fail")
    }
  }
}
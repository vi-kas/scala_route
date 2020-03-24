package io.github.vi_kas

import java.util.UUID

import io.github.vi_kas.config.OrderSlickConfiguration
import io.github.vi_kas.models.Order
import javax.cache.Cache
import org.apache.ignite.cache.store.CacheStoreAdapter
import org.apache.ignite.lang.IgniteBiInClosure
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Try

class IgnitePostgresStore extends CacheStoreAdapter[UUID, Order] {

  val orderSlickConfiguration = new OrderSlickConfiguration

  val database = orderSlickConfiguration.pgDatabase
  val table = orderSlickConfiguration.table

  override def loadCache(clo: IgniteBiInClosure[UUID, Order], args: AnyRef*): Unit =
    for {
      orders <- database.run(table.map(u => u).result).recoverWith { case _ => Future(Seq.empty[Order]) }
    } yield {
      orders.foreach(order => clo.apply(order.id, order))
    }

  override def write(entry: Cache.Entry[_ <: UUID, _ <: Order]): Unit =
    Try {
      database.run {
        DBIO.seq(table.insertOrUpdate(entry.getValue))
          .transactionally
      }
    }

  override def delete(key: Any): Unit =
    Try {
      database.run{
        DBIO.seq(
          table.filter(_.id === key.asInstanceOf[UUID]).delete
        ).transactionally
      }
    }

  override def load(key: UUID): Order = {
    val loaded: Future[Option[Order]] = database.run(table.filter(_.id === key).result.headOption)

    Await.result(loaded, 10 second).getOrElse(null)
  }
}

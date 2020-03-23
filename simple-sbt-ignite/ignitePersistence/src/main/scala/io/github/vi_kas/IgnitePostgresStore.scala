package io.github.vi_kas

import java.util.UUID

import io.github.vi_kas.models.Order
import javax.cache.Cache
import org.apache.ignite.cache.store.CacheStoreAdapter
import org.apache.ignite.lang.IgniteBiInClosure
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted
import slick.lifted.ProvenShape

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}

trait PostgresSlick {

  val profile = PostgresProfile.api

  val pgDatabase = Database.forConfig("ignite.postgres")

  val tableName: String
}

class IgnitePostgresStore extends CacheStoreAdapter[String, Order] with PostgresSlick {

  implicit val ec = scala.concurrent.ExecutionContext.global

  import profile._

  val tableName = "orders"

  class OrderTable(tag: Tag) extends Table[Order](tag, "orders_t"){

    val id = column[UUID]("id", O.PrimaryKey)

    val title = column[String]("title")
    val quantity = column[Int]("quantity")
    val price = column[Double]("price")

    def * : ProvenShape[Order] =
      (id, title, quantity, price) <> (Order.tupled, Order.unapply)
  }

  val table = lifted.TableQuery[OrderTable]

  val startup: Unit = createSchema

  private def createSchema(): Unit = {
    pgDatabase.run(table.exists.result) onComplete {
      case Success(exists) =>
        println("Schema already exists")
      case Failure(e) => {
        println(s"Creating schema for $tableName")
        val dbioAction = (
          for {
            _ <- table.schema.create
          } yield ()
          ).transactionally
        pgDatabase.run(dbioAction)
      }
    }
  }


  override def loadCache(clo: IgniteBiInClosure[String, Order], args: AnyRef*): Unit = {
    for {
      orders <- pgDatabase.run(table.map(u => u).result) recoverWith { case _ => Future(Seq.empty[Order]) }
    } yield {
      orders.foreach(order => clo.apply(order.id.toString, order))
    }
  }

  override def write(entry: Cache.Entry[_ <: String, _ <: Order]): Unit =  Try {
    val dbioAction = DBIO.seq(table.insertOrUpdate(entry.getValue)).transactionally
    pgDatabase.run(dbioAction)
  }

  override def delete(key: Any): Unit = {
    Try {
      val dbioAction = DBIO.seq(
        table.filter(_.id === key.asInstanceOf[UUID]).delete
      ).transactionally

      pgDatabase.run(dbioAction)
    }
  }

  override def load(key: String): Order = {
    val loadedorder = pgDatabase.run(table.filter(_.id === UUID.fromString(key)).result.headOption)
    Await.result(loadedorder, 10 second).getOrElse(null)
  }
}

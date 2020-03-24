package io.github.vi_kas.config

import java.util.UUID

import io.github.vi_kas.models.Order
import org.slf4j.{Logger, LoggerFactory}
import slick.jdbc.PostgresProfile
import slick.lifted.ProvenShape
import slick.{jdbc, lifted}

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

class OrderSlickConfiguration {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  val databaseProfile: jdbc.PostgresProfile.API = PostgresProfile.api

  import databaseProfile._

  val pgDatabase = Database.forConfig("ignite.postgres")

  implicit val ec: ExecutionContextExecutor = scala.concurrent.ExecutionContext.global

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

  def createSchema(): Unit =
    pgDatabase
      .run(table.exists.result)
      .onComplete {
        case Success(_) =>
          logger.info("Schema already exists")

        case Failure(_) => {
          logger.info(s"Creating schema for $tableName")

          pgDatabase
            .run(table.schema.create.transactionally)
        }
      }
}

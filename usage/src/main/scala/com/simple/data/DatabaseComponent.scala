package com.simple.data

import java.sql.Timestamp
import java.util.UUID

import com.simple.data.Model.Entity
import slick.jdbc.JdbcProfile
import slick.lifted

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag


trait DatabaseComponent {

  val dbConfig: String
  val driver: JdbcProfile

  import driver.api._

  lazy val db = Database.forConfig(dbConfig)
}

trait EntityTableDefinition {
  this: DatabaseComponent =>

  import driver.api._

  abstract class EntityTable[E <: Entity: ClassTag](tag: Tag, tableName: String, schemaName: Option[String] = None)
    extends Table[E](tag, schemaName, tableName) {

    val id = column[UUID]("id", O.PrimaryKey)
    val created = column[Timestamp]("created_at")
    val updated = column[Timestamp]("updated_at")
  }

}

trait RepoDefinition extends EntityTableDefinition {
  this: DatabaseComponent =>

  import driver.api._

  abstract class EntityRepo[E <: Entity, T <: EntityTable[E]](implicit ec: ExecutionContext)
    extends Repository[E] {

    val table: lifted.TableQuery[T]

    override def all: Future[Seq[E]] = db.run {
      table.to[Seq].result
    }

    override def byId(id: UUID): Future[Option[E]] = db.run {
      table.filter(_.id === id).result.headOption
    }

    override def insert(entity: E): Future[E] = db.run {
      table returning table += entity
    }

    override def update(entity: E): Future[Int] = db.run {
      table insertOrUpdate entity
    }

    override def delete(id: UUID): Future[Boolean] = db.run {
      table.filter(_.id === id).delete.map(_ > 0)
    }

  }

}

sealed trait Repository[E <: Entity] {

  def all: Future[Seq[E]]

  def byId(id: UUID): Future[Option[E]]

  def insert(entity: E): Future[E]

  def update(entity: E): Future[Int]

  def delete(id: UUID): Future[Boolean]
}
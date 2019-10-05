package com.simple.data

import com.simple.data.Model.User

import scala.concurrent.{ExecutionContext, Future}
import scala.language.{existentials, postfixOps}

trait UserDBComponent extends RepoDefinition {
  this: DatabaseComponent =>

  import driver.api._

  class UserTable(tag: Tag) extends EntityTable[User](tag, "users"){

    val name = column[String]("name")
    val email = column[String]("email")

    def * =
      (id, name, email.?, created, updated.?, deleted.?) <> (User.tupled, User.unapply)
  }

  class UserRepo(implicit ec: ExecutionContext) extends EntityRepo[User, UserTable] {

    override val table = TableQuery[UserTable]

    def byName(name: String): Future[Seq[User]] = db.run {
      table.filter(_.name === name).to[Seq].result
    }

    def byEmail(email: String): Future[Option[User]] = db.run {
      table.filter(_.email === email).result.headOption
    }
  }

}
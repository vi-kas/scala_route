package com.simple.services

import java.time.LocalDateTime
import java.util.UUID

import com.simple.Response._
import com.simple.data.Model.User
import com.simple.data.UserDBComponent

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UserService {

  def getUserById(id: UUID): Future[ServiceResponse[User]]
  def getUserByEmail(email: String): Future[ServiceResponse[User]]

  def getUsersByName(name: String): Future[ServiceResponse[Seq[User]]]
  def getAllUsers: Future[ServiceResponse[Seq[User]]]

  def createUser(user: User): Future[ServiceResponse[User]]
  def deleteUser(id: UUID): Future[ServiceResponse[Boolean]]
}

trait UserServiceComponent {
  self: UserDBComponent =>

  val userService: UserService

  val repo: UserRepo

  class UserServiceImpl extends UserService {

    def getUserById(id: UUID): Future[ServiceResponse[User]] =
      repo
        .byId(id)
        .map {
          case None => Left(ErrorResponse(s"User with $id not found.", 0))
          case Some(user) => Right(user)
        }

    def getUsersByName(name: String): Future[ServiceResponse[Seq[User]]] = repo
      .byName(name)
      .map {
        case users if users.isEmpty => Left(ErrorResponse(s"No users with name: $name found.", 0))
        case users => Right(users)
      }

    def getUserByEmail(email: String): Future[ServiceResponse[User]] =
      repo
        .byEmail(email)
        .map {
          case None => Left(ErrorResponse(s"User with email: $email not found.", 0))
          case Some(user) => Right(user)
        }

    def getAllUsers: Future[ServiceResponse[Seq[User]]] =
      repo
        .all
        .map {
          case users if users.isEmpty => {
            println(s"Lol")
            Left(ErrorResponse(s"No users found.", 200))
          }
          case users => Right(users)
        }

    def createUser(user: User): Future[ServiceResponse[User]] = {
      val created = java.sql.Timestamp.valueOf(LocalDateTime.now())

      val userToInsert =
        user
          .copy(id = UUID.randomUUID, created = created)

      repo
        .insert(userToInsert)
        .map {
          case u if u.id != null => Right(u)
          case _ => Left(ErrorResponse(s"User could not be created.", 0))
        }
    }

    def deleteUser(id: UUID): Future[ServiceResponse[Boolean]] = {
      repo
        .delete(id)
        .map {
          case true => Right(true)
          case false => Left(ErrorResponse(s"Could not delete user $id.", 0))
        }
    }

  }
}
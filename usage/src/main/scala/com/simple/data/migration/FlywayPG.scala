package com.simple.data.migration

import com.typesafe.config.Config
import org.flywaydb.core.Flyway

import scala.util.Try

class FlywayService(config: Config) {

  private val url = config.getString("pgconfe.properties.url")
  private val dbuser = config.getString("pgconfe.properties.user")
  private val dbpassword = config.getString("pgconfe.properties.password")

  val flyway: Flyway =
    Flyway
      .configure()
      .baselineOnMigrate(true)
      .dataSource(url, dbuser, dbpassword).load()

  def migrateDatabaseSchema(): Int =
    Try(flyway.migrate()).getOrElse {
      flyway.repair()
      flyway.migrate()
    }

  def dropDatabase(): Unit = flyway.clean()
}
package io.github.vi_kas

import com.amazon.ask.{Skill, Skills}
import io.github.vi_kas.handlers._
import org.slf4j.LoggerFactory

object AlexaSkill {

  val logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {

    logger.info(s"[Logger] Starting AlexaSkill...")

    println(s"Starting AlexaSkill...")

    def getSkill(): Skill = {

      Skills
        .standard()
        .addRequestHandlers(
          new LaunchRequestHandler,
          new CancelStopIntentHandler(),
          new HelpIntentHandler(),
          new LaunchRequestHandler(),
          new SessionEndedRequestHandler())
        .build()
    }

    new HelloWorldStreamHandler(getSkill())
  }
}
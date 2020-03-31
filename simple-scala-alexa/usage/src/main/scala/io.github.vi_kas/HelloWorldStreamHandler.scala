package io.github.vi_kas

import com.amazon.ask.{Skill, SkillStreamHandler, Skills}
import io.github.vi_kas.handlers._

class HelloWorldStreamHandler extends SkillStreamHandler {

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

}

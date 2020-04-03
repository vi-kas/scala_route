package io.github.vi_kas.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates.intentName

class CancelStopIntentHandler extends RequestHandler {

  override def canHandle(input: HandlerInput): Boolean =
    input.matches(
      intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent"))
    )

  override def handle(input: HandlerInput): Optional[Response] = {
    val speechText = "Goodbye!"

    input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("HelloWorld", speechText)
      .withShouldEndSession(true)
      .build()
  }

}

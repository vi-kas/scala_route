package io.github.vi_kas.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates.intentName

class FallbackIntentHandler extends RequestHandler {

  override def canHandle(input: HandlerInput): Boolean =
    input.matches(intentName("AMAZON.FallbackIntent"))

  override def handle(input: HandlerInput): Optional[Response] = {
    val speechText = "Sorry, I don't know that, You can say Help!"

    input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("HelloWorld", speechText)
      .withReprompt(speechText)
      .build()
  }

}

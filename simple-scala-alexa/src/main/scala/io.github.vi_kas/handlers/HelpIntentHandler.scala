package io.github.vi_kas.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates.intentName

class HelpIntentHandler extends RequestHandler {

  override def canHandle(input: HandlerInput): Boolean =
    input.matches(intentName("AMAZON.HelpIntent"))

  override def handle(input: HandlerInput): Optional[Response] = {
    val speechText = "Welcome to Alexa Skills Kit, you can say Hello"

    input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("HelloWorld", speechText)
      .withReprompt(speechText)
      .build()
  }

}
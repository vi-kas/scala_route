package io.github.vi_kas.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.{Response, SessionEndedRequest}
import com.amazon.ask.request.Predicates.requestType

class SessionEndedRequestHandler extends RequestHandler {

  override def canHandle(input: HandlerInput): Boolean =
    input.matches(requestType(classOf[SessionEndedRequest]))

  override def handle(input: HandlerInput): Optional[Response] = {
    input
      .getResponseBuilder
      .build()
  }

}

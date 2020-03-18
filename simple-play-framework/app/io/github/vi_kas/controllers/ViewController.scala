package io.github.vi_kas.controllers

import controllers.AssetsFinder
import io.github.vi_kas.forms.Forms._
import org.slf4j.{Logger, LoggerFactory}
import play.api.i18n.{I18nSupport, Langs}
import play.api.mvc._

class ViewController(langs: Langs, cc: ControllerComponents)(implicit assetFinder: AssetsFinder)
  extends AbstractController(cc) with I18nSupport {

  private val logger: Logger = LoggerFactory.getLogger(getClass)
  private val authenticationCallback: Call = routes.ViewController.verifyLogin()

  def index: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    logger.info(s"[ViewController] - index")

    Ok(views.html.index())
  }

  def login: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    logger.info(s"[ViewController] - login")

    Ok(views.html.login(userForm, authenticationCallback))
  }

  def verifyLogin: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    logger.info(s"[ViewController] - verifyLogin")

    userForm.bindFromRequest.fold(
      formWithErrors => {
        logger.error(s"Form with errors ${formWithErrors.data}")
        logger.error(s"Form has errors: ${formWithErrors.errors}")
        BadRequest("Bad Request for Login")
      },
      loginData => {
        logger.info(s"[ViewController] - loginData: $loginData")

        if(loginData.password != "p")
          Unauthorized("Incorrect Password.")
        else
          Ok(s"Login for user: ${loginData.username} is successFul.")
      })
  }
}
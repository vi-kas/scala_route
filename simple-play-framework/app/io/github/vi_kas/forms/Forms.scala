package io.github.vi_kas.forms

import io.github.vi_kas.models.UserLogin
import play.api.data.Form
import play.api.data.Forms._

object Forms {

  val userForm = Form(
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )(UserLogin.apply)(UserLogin.unapply)
  )

}

package controllers

import play.api._
import play.api.mvc._
import play.api.mvc.BodyParsers.parse

object Application extends Controller {

  val index = Action(parse.text(1024 * 1024 * 10)) { request =>
//    assert(request.tags.get("HTTP_SERVER") == Some("akka-http"))
    Ok(request.body)
  }

}

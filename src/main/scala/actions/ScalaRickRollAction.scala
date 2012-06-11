package actions

import play.api.mvc._
import play.api.Play

/**
 * Action for rick rolling
 */
case class ScalaRickRollAction[A](action: Action[A]) extends Action[A] {
  def apply(request: Request[A]): Result = {
    // Check whether we are going to rick roll or not
    val dontRickRoll = request.headers.get("X-Dont-Rick-Roll")
    // Get the result of the action
    val result = action(request)
    // See what sort of result it is
    dontRickRoll.map(_ => handleResult(result)).getOrElse(result)
  }

  def handleResult(result: Result): Result = {
    result match {
      // async request, map the promise of the result a recursive call to handle the result
      case async: AsyncResult => AsyncResult(async.result.map(result => handleResult(result)))
      // plain request, add the X-Rick-Roll header
      case plain: PlainResult => plain.withHeaders("X-Rick-Roll" -> "Never gonna give you up")
    }
  }

  lazy val parser = action.parser
}




/**
 * Action for rick rolling with a custom header name
 */
case class ScalaCustomRickRollAction[A](headerName: String = "X-Rick-Roll")
                                       (action: Action[A]) extends Action[A] {
  def apply(request: Request[A]): Result = {
    val dontRickRoll = request.headers.get("X-Dont-Rick-Roll")
    val result = action(request)
    dontRickRoll.map(_ => handleResult(result)).getOrElse(result)
  }

  def handleResult(result: Result): Result = {
    result match {
      case async: AsyncResult => AsyncResult(async.result.map(result => handleResult(result)))
      case plain: PlainResult => plain.withHeaders(headerName -> "Never gonna give you up")
    }
  }

  lazy val parser = action.parser
}




/**
 * Action for rick rolling with a custom header name that's globally configurable
 */
case class ScalaGlobalCustomRickRollAction[A](headerName: String = ScalaRickRollConfig.defaultHeaderName)
                                             (action: Action[A]) extends Action[A] {
  def apply(request: Request[A]): Result = {
    val dontRickRoll = request.headers.get("X-Dont-Rick-Roll")
    val result = action(request)
    dontRickRoll.map(_ => handleResult(result)).getOrElse(result)
  }

  def handleResult(result: Result): Result = {
    result match {
      case async: AsyncResult => AsyncResult(async.result.map(result => handleResult(result)))
      case plain: PlainResult => plain.withHeaders(headerName -> "Never gonna give you up")
    }
  }

  lazy val parser = action.parser
}

/**
 * Loads configuration for rick rolling
 */
object ScalaRickRollConfig {
  lazy val defaultHeaderName = Play.maybeApplication.flatMap(
    _.configuration.getString("rickroll.header")
  ).getOrElse("X-Rick-Roll")
}





/**
 * Handler for wrapping requests in a rick roll action
 */
object ScalaRickRollHandler {
  def apply(handler: Option[Handler],
            headerName: String = ScalaRickRollConfig.defaultHeaderName): Option[Handler] = {
    handler.map { _ match {
      case action: Action[_] => ScalaGlobalCustomRickRollAction(headerName)(action)
      case other: Handler => other
    }}
  }
}
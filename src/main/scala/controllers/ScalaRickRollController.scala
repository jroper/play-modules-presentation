package controllers

import play.api.mvc.{Controller, Action}
import actions.{ScalaCustomRickRollAction, ScalaRickRollAction}

class ScalaRickRollController extends Controller {

  val rickRoll = ScalaRickRollAction {
    Action {
      Ok("Rick rolled")
    }
  }



  val rickRollNotCustom = ScalaCustomRickRollAction() {
    Action {
      Ok("Rick rolled")
    }
  }



  val rickRollCustom = ScalaCustomRickRollAction("X-Custom-Rick-Roll") {
    Action {
      Ok("Rick rolled")
    }
  }

}

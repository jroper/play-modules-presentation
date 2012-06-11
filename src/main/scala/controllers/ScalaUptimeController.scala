package controllers

import play.api.mvc.{Action, Controller}
import play.api.Play
import plugins.ScalaUptimePlugin


class ScalaUptimeController extends Controller {

  val upTime = Action {
    Ok(Play.current.plugin[ScalaUptimePlugin].map(_.upTime.toString).getOrElse("0"))
  }




  val simpleUpTime = Action {
    Ok(ScalaUptimePlugin.getUpTime.toString)
  }




  val implicitUpTime = Action {
    import play.api.Play.current
    Ok(ScalaUptimePlugin.upTime.toString)
  }

}

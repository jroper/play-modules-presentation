import actions.ScalaRickRollHandler
import play.api.GlobalSettings
import play.api.mvc.RequestHeader

class Global extends GlobalSettings {
  override def onRouteRequest(request: RequestHeader) = ScalaRickRollHandler(super.onRouteRequest(request))
}

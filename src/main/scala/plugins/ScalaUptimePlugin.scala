package plugins

import play.api.{Play, Plugin, Application}

/**
 * Uptime plugin
 */
class ScalaUptimePlugin(val app: Application) extends Plugin {
  private var startTime = 0: Long

  /**
   * Called when the application is started. This includes when the application is reloaded during development.
   */
  override def onStart() {
    startTime = System.currentTimeMillis()
    println("I have started!")
  }

  /**
   * Called when the application is stopped. This includes when the application is reloaded during development.
   */
  override def onStop() {
    println("I have stopped!")
  }

  /**
   * Get the current uptime
   *
   * @return The uptime
   */
  def upTime = System.currentTimeMillis() - startTime

  /**
   * Enabled by default, unless we set a disabled flag
   */
  override def enabled = !app.configuration.getString("scalaUptimePlugin")
    .filter(_ == "disabled").isDefined
}

object ScalaUptimePlugin {

  /**
   * Get the uptime from the given app
   *
   * @param app The app
   * @return The uptime
   */
  def getUpTime(app: Application): Long = app.plugin[ScalaUptimePlugin].map(_.upTime).getOrElse(0)

  /**
   * Get the uptime from the current app
   *
   * @return The uptime
   */
  def getUpTime: Long = getUpTime(Play.current)

  /**
   * Get the uptime from the given app
   *
   * @param app The app
   * @return The uptime
   */
  def upTime(implicit app: Application) = app.plugin[ScalaUptimePlugin].map(_.upTime).getOrElse(0)
}
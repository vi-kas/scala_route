
import _root_.controllers.AssetsComponents
import com.softwaremill.macwire._
import io.github.vi_kas.controllers.ViewController
import play.api.ApplicationLoader.Context
import play.api._
import play.api.i18n._
import play.api.routing.Router
import router.Routes

/**
  * Application loader that wires up the application dependencies using Macwire
  */
class ClientApplicationLoader extends ApplicationLoader {
  def load(context: Context): Application = new ClientApplicationComponents(context).application
}

class ClientApplicationComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with BuiltInComponents
    with AssetsComponents
    //    with AhcWSComponents
    with I18nComponents
    with play.filters.HttpFiltersComponents {

  implicit val af = assetsFinder

  lazy val viewController: ViewController = wire[ViewController]

  // set up logger
  LoggerConfigurator(context.environment.classLoader).foreach {
    _.configure(context.environment, context.initialConfiguration, Map.empty)
  }

  lazy val router: Router = {
    // add the prefix string in local scope for the Routes constructor
    val prefix: String = "/"
    wire[Routes]
  }

}
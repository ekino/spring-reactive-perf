import io.gatling.core.Predef._
import io.gatling.core.controller.inject.open.OpenInjectionStep
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

abstract class BaseSimulation extends Simulation {

  protected val baseUrl = "http://localhost"
  protected val contentType = "application/json"
  protected val maxId = 2000

  protected val simUsers: Int = System.getProperty("users", "1").toInt
  protected val requestCount: Int = System.getProperty("requests", "1").toInt
  protected val minDelay: Int = System.getProperty("minDelay", "0").toInt
  protected val maxDelay: Int = System.getProperty("maxDelay", "1").toInt
  protected val isRampUp: Boolean = System.getProperty("ramp", "true").toBoolean
  protected val rampUpTime: Int = System.getProperty("rampTime", "20").toInt

  protected val injectionStrategy: OpenInjectionStep = if (isRampUp) rampUsers(simUsers) during rampUpTime else atOnceUsers(simUsers)

  println(s"\nStarting with $simUsers simultaneous user, $requestCount requests each.\n\n")

  def getConf(port: Int): HttpProtocolBuilder = {
    http
      .baseUrl(s"$baseUrl:$port")
      .acceptHeader("application/json;charset=UTF-8")
  }
}

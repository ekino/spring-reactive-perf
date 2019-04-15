import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

abstract class WebSimulation extends BaseSimulation {

  def getScenarioName: String

  def getBaseUrl: String

  val getSimpleRequest: ChainBuilder = repeat(requestCount) {
    exec(http("simple-request")
      .get(s"$getBaseUrl/simple")
      .header("Content-Type", contentType)
      .check(status.is(200)))
  }

  val getRequestWithDelay: ChainBuilder = repeat(requestCount) {
    exec(http("delay-request")
      .get(s"$getBaseUrl/delay")
      .queryParam("minDelay", minDelay)
      .queryParam("maxDelay", maxDelay)
      .header("Content-Type", contentType)
      .check(status.is(200)))
  }

  val getRequestWithBothDelay: ChainBuilder = repeat(requestCount) {
    exec(http("delay-both-request")
      .get(s"$getBaseUrl/delay-both")
      .queryParam("minDelay", minDelay)
      .queryParam("maxDelay", maxDelay)
      .header("Content-Type", contentType)
      .check(status.is(200)))
  }

  println(s"With min delay $minDelay ms and max delay $maxDelay ms")

  setUp(scenario(getScenarioName)
    .exec(getSimpleRequest)
    .exec(getRequestWithDelay)
    .exec(getRequestWithBothDelay)
    .inject(injectionStrategy))
    .protocols(getConf(9090))
}

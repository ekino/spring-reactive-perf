import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.util.Random

abstract class DBSimulation extends BaseSimulation {

  private val random = new Random()
  private val endpoint = "/persons"

  def getScenarioName: String

  def getBase: String

  def randomId(): Int = random.nextInt(maxId - 1) + 1

  def randomAge(): Int = random.nextInt(50) + 20

  val getAllPersonsTest: ChainBuilder = repeat(requestCount) {
    exec(http("get-all-persons")
      .get(s"$getBase$endpoint")
      .header("Content-Type", contentType)
      .check(status.is(200)))
  }

  val getOnePersonTest: ChainBuilder = repeat(requestCount) {
    exec(http("get-one-person")
      .get(s"$getBase$endpoint/${randomId()}")
      .header("Content-Type", contentType)
      .check(status.is(200)))
  }

  val getPersonsByAge: ChainBuilder = repeat(requestCount) {
    exec(http("get-person-by-age")
      .get(s"$getBase$endpoint")
      .queryParam("age", randomAge())
      .header("Content-Type", contentType)
      .check(status.is(200)))
  }

  val createPerson: ChainBuilder = repeat(requestCount) {
    exec(http("create-person")
      .post(s"$getBase$endpoint")
      .header("Content-Type", contentType)
      .check(status.is(200)))
  }

  setUp(scenario(getScenarioName)
    .exec(getOnePersonTest)
    .exec(getPersonsByAge)
    .exec(getAllPersonsTest)
    .exec(createPerson)
    .inject(injectionStrategy))
    .protocols(getConf(9090))
}

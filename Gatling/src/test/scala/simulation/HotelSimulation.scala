package simulation

import _root_.scenario.Scenarios
import config.Constants
import io.gatling.core.Predef._

import scala.concurrent.duration._

class HotelSimulation extends Simulation {

  setUp(Scenarios.scnHotelList
    .inject(constantConcurrentUsers(1) during(5 seconds), rampConcurrentUsers(1) to (2)during(10 seconds)))
    .protocols(Constants.httpProtocol).maxDuration(30 seconds)
    .assertions(global.successfulRequests.percent.is(100))
}

package scenario

import config.Constants
import io.gatling.core.Predef._
import io.gatling.core.feeder.FeederBuilder
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import request.HotelRequests

object Scenarios {

  def createScenario(name: String, feed: FeederBuilder, chains: ChainBuilder*): ScenarioBuilder = {
    if (Constants.repeatTimes > 0) {
      scenario(name).feed(feed).repeat(Constants.repeatTimes) {
        pace(Constants.pace).exec(chains).pause(Constants.pause)
      }
    } else {
        scenario(name).feed(feed).forever() {
          pace(Constants.pace).exec(chains).pause(Constants.pause)
      }
    }
  }

  val scnHotalDetails = createScenario("SCN_HOTEL_DETAILS", Constants.hotelcsvFeeder.circular, HotelRequests.getHotelList)
  val scnHotelList = createScenario("SCN_HOTEL_LIST", Constants.hotelcsvFeeder.circular, HotelRequests.getHotelList)

}

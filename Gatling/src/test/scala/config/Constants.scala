package config

import com.typesafe.config._
import io.gatling.http.Predef._
import io.gatling.core.Predef._

object Constants {

  val conf = ConfigFactory.load("app.properties")

  //Application Details
  val baseUrl = conf.getString("baseURL")
  val appID = conf.getString("appId")
  val appKey = conf.getString("appKey")

  //Scenario Details
  val repeatTimes = conf.getString("repeat").toInt
  val pause =  conf.getString("pauseBetweenRequestsMs").toInt
  val pace = conf.getString("paceInMs").toInt

  //Simulation Details
  val httpProtocol = http.baseUrl(baseUrl)

  //Hotel Scenario Details
  val hotelcsvFeeder = csv(conf.getString("hotalDataFile"))

}

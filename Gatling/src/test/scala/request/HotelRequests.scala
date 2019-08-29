package request

import config._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object HotelRequests {

  var firstHotelIdVector=""
  var secondHotelIdVector=""

  val getHotelList = exec(http("getHotelList").get("/api/voyager/get_hotels_by_cityid/?app_id=" + Constants.appID + "&app_key=" + Constants.appKey + "&city_id=${CityId}")
    .check(jsonPath("$.data..hotel_geo_node._id").findAll.saveAs("myHotelIds")))
    .exec(session => {
      println(session("myHotelIds").as[String])
      val hotelIdVector = session("myHotelIds").as[Vector[String]]
      firstHotelIdVector = hotelIdVector(0)
      secondHotelIdVector = hotelIdVector(1)
      println(firstHotelIdVector)
      println(secondHotelIdVector)
      session.set("firstHotelId", firstHotelIdVector).set("secondHotelId", secondHotelIdVector)
    })

  val getHotelDetails = exec(http("getHotelDetails").get("/api/voyager/?app_id="+Constants.appID+"&app_key="+Constants.appKey+"&method=hotels.get_hotels_data&id_list=%5B${firstHotelIdVector}%2C+${secondHotelIdVector}%5D&id_type=_id")
    .check(bodyString.saveAs("myResponse")))
    .exec(session =>{
      println(session("myResponse").as[String])
      session
    })


}

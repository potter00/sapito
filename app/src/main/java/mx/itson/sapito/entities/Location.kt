package mx.itson.sapito.entities

import com.google.gson.annotations.SerializedName

class Location {

    var latitude : Float? = null
    var longitude : Float? = null
    var elevation : Float? = null

    @SerializedName("Current_weather_unit")
    var current_weather_unit : WeatherUnits? = null
    @SerializedName("current_weather")
    var current_weather : Weather? = null

}
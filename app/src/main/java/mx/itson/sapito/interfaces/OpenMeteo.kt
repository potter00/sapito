package mx.itson.sapito.interfaces

import mx.itson.sapito.entities.Location
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteo {

    @GET("forecast")
    fun getLocation(@Query("latitude") latitude: String,
                    @Query("longitude") longitude: String,
                    @Query("current_weather") currentWeather: Boolean)
    : Call<Location>
}
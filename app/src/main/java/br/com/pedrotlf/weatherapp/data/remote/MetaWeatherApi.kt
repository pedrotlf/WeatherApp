package br.com.pedrotlf.weatherapp.data.remote

import br.com.pedrotlf.weatherapp.data.remote.dto.DetailedWeatherDTO
import br.com.pedrotlf.weatherapp.data.remote.dto.LocationInfoDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetaWeatherApi {

    @GET("location/{id}")
    suspend fun getDetailedWeatherInfo(@Path("id") locationId: String): DetailedWeatherDTO

    @GET("location/search")
    suspend fun getCloseLocations(@Query("lattlong") lattLong: String ): List<LocationInfoDTO>
}
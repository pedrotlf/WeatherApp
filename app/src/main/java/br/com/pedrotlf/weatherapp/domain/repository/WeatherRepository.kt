package br.com.pedrotlf.weatherapp.domain.repository

import br.com.pedrotlf.weatherapp.data.remote.dto.DetailedWeatherDTO
import br.com.pedrotlf.weatherapp.data.remote.dto.LocationInfoDTO
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherRepository {

    suspend fun getDetailedWeatherInfo(@Path("id") locationId: String): DetailedWeatherDTO

    suspend fun getCloseLocations(@Query("lattlong") lattLong: String ): List<LocationInfoDTO>
}
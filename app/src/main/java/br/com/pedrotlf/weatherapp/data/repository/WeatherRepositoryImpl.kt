package br.com.pedrotlf.weatherapp.data.repository

import br.com.pedrotlf.weatherapp.data.remote.MetaWeatherApi
import br.com.pedrotlf.weatherapp.data.remote.dto.DetailedWeatherDTO
import br.com.pedrotlf.weatherapp.data.remote.dto.LocationInfoDTO
import br.com.pedrotlf.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: MetaWeatherApi
) : WeatherRepository {

    override suspend fun getDetailedWeatherInfo(locationId: String): DetailedWeatherDTO {
        return api.getDetailedWeatherInfo(locationId)
    }

    override suspend fun getCloseLocations(lattLong: String): List<LocationInfoDTO> {
        return api.getCloseLocations(lattLong)
    }
}
package br.com.pedrotlf.weatherapp.data.repository

import br.com.pedrotlf.weatherapp.MockResponseFileReader
import br.com.pedrotlf.weatherapp.data.remote.dto.DetailedWeatherDTO
import br.com.pedrotlf.weatherapp.data.remote.dto.LocationInfoDTO
import br.com.pedrotlf.weatherapp.domain.repository.WeatherRepository

class FakeWeatherRepository : WeatherRepository {

    override suspend fun getDetailedWeatherInfo(locationId: String): DetailedWeatherDTO {
        return MockResponseFileReader("weather_detail_mock.json").getDesserializedContent()
    }

    override suspend fun getCloseLocations(lattLong: String): List<LocationInfoDTO> {
        TODO("Not yet implemented")
    }
}
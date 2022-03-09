package br.com.pedrotlf.weatherapp.domain.use_case.get_detailed_weather

import br.com.pedrotlf.weatherapp.MockResponseFileReader
import br.com.pedrotlf.weatherapp.data.remote.MetaWeatherApi
import br.com.pedrotlf.weatherapp.data.remote.dto.DetailedWeatherDTO
import br.com.pedrotlf.weatherapp.data.repository.FakeWeatherRepository
import br.com.pedrotlf.weatherapp.data.repository.WeatherRepositoryImpl
import br.com.pedrotlf.weatherapp.di.AppModule
import br.com.pedrotlf.weatherapp.domain.use_case.GetDetailedWeatherUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetDetailedWeatherUseCaseTest{

    private lateinit var getDetailedWeatherFakeUseCase: GetDetailedWeatherUseCase
    private lateinit var fakeRepository: FakeWeatherRepository

    private lateinit var getDetailedWeatherUseCase: GetDetailedWeatherUseCase
    private lateinit var repository: WeatherRepositoryImpl

    @Before
    fun setUp(){
        fakeRepository = FakeWeatherRepository()
        getDetailedWeatherFakeUseCase = GetDetailedWeatherUseCase(fakeRepository)

        repository = WeatherRepositoryImpl(AppModule.provideMetaWeatherApi())
        getDetailedWeatherUseCase = GetDetailedWeatherUseCase(repository)
    }

    @Test
    fun `Check read simple file`(){
        val reader = MockResponseFileReader("test.json")
        assertEquals(reader.content, "\"success\"")
    }

    @Test
    fun `Check read mock file`(){
        val reader = MockResponseFileReader("weather_detail_mock.json")
        reader.getDesserializedContent<DetailedWeatherDTO>()
    }

    @Test
    fun `Check first day temperature is not null and bigger than 0`() = runBlocking {
        val detailedWeather = fakeRepository.getDetailedWeatherInfo("")
        val firstDayTemp = detailedWeather.consolidatedWeather?.firstOrNull()?.theTemp

        assert(firstDayTemp != null && firstDayTemp > 0)
    }
}
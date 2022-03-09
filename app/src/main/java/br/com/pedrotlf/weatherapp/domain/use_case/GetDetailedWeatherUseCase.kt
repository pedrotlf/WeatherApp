package br.com.pedrotlf.weatherapp.domain.use_case

import br.com.pedrotlf.weatherapp.Resource
import br.com.pedrotlf.weatherapp.data.remote.dto.toDetailedWeather
import br.com.pedrotlf.weatherapp.domain.model.DetailedWeather
import br.com.pedrotlf.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDetailedWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    operator fun invoke(locationId: String): Flow<Resource<DetailedWeather>> = flow {
        try {
            emit(Resource.Loading())
            val weather = repository.getDetailedWeatherInfo(locationId).toDetailedWeather()
            if(weather.consolidatedWeather.isNullOrEmpty())
                emit(Resource.Error("Unexpected error"))
            else
                emit(Resource.Success(weather))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your connection"))
        }
    }
}
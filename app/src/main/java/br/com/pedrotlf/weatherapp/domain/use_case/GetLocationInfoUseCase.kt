package br.com.pedrotlf.weatherapp.domain.use_case

import br.com.pedrotlf.weatherapp.Resource
import br.com.pedrotlf.weatherapp.data.remote.dto.toLocationInfo
import br.com.pedrotlf.weatherapp.domain.model.LocationInfo
import br.com.pedrotlf.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLocationInfoUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    operator fun invoke(locationId: String): Flow<Resource<List<LocationInfo>>> = flow {
        try {
            emit(Resource.Loading())
            val weather = repository.getCloseLocations(locationId).map { it.toLocationInfo() }
            emit(Resource.Success(weather))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your connection"))
        }
    }
}
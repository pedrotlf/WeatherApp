package br.com.pedrotlf.weatherapp.presentation.home

import androidx.lifecycle.*
import br.com.pedrotlf.weatherapp.Resource
import br.com.pedrotlf.weatherapp.domain.model.DetailedWeather
import br.com.pedrotlf.weatherapp.domain.use_case.GetDetailedWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherUseCase: GetDetailedWeatherUseCase
) : ViewModel() {

    private val _weatherDetails = MutableLiveData<Resource<DetailedWeather>>()
    val weatherDetails: LiveData<Resource<DetailedWeather>> = _weatherDetails

    init {
        //TODO selected location ID from prefs value
        getWeatherDetails("4118")
    }

    private fun getWeatherDetails(locationId: String) {
        weatherUseCase(locationId).onEach {
            _weatherDetails.value = it
        }.launchIn(viewModelScope)
    }

    fun selectLocationId(id: String){
        //TODO save selected location to prefs
        getWeatherDetails(id)
    }
}
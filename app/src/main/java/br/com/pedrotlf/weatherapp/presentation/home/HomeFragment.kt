package br.com.pedrotlf.weatherapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.pedrotlf.weatherapp.BaseFragment
import br.com.pedrotlf.weatherapp.R
import br.com.pedrotlf.weatherapp.Resource
import br.com.pedrotlf.weatherapp.databinding.FragmentHomeBinding
import br.com.pedrotlf.weatherapp.domain.model.ConsolidatedWeather
import br.com.pedrotlf.weatherapp.domain.model.DetailedWeather
import kotlin.math.roundToInt

class HomeFragment : BaseFragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.observeWeatherDetails()

        observeDirection()

        binding.homeForecastButton.setOnClickListener {
            viewModel.forecastButtonClicked()
        }
    }

    private fun observeDirection() {
        viewModel.direction.observe(viewLifecycleOwner) {
            when(it){
                is HomeViewModel.Directions.GoToForecast -> {
                    goToForecast(it.list)
                }

                else -> {
                    //do nothing
                }
            }

        }
    }

    private fun goToForecast(weatherList: List<ConsolidatedWeather>) {
        val action = HomeFragmentDirections.actionHomeFragmentToForecastFragment(weatherList.toTypedArray())
        findNavController().navigate(action)
    }

    private fun FragmentHomeBinding.observeWeatherDetails() {
        viewModel.weatherDetails.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    homeErrorLayout.isVisible = false
                    homeLoadingLayout.isVisible = true
                    homeSuccessLayout.isVisible = false
                }
                is Resource.Success -> {
                    homeErrorLayout.isVisible = false
                    homeLoadingLayout.isVisible = false
                    homeSuccessLayout.isVisible = true
                    setWeatherInfo(it.data)
                }
                is Resource.Error -> {
                    homeErrorLayout.isVisible = true
                    homeLoadingLayout.isVisible = false
                    homeSuccessLayout.isVisible = false
                    homeErrorText.text = it.message
                }
            }
        }
    }

    private fun FragmentHomeBinding.setWeatherInfo(detailedWeather: DetailedWeather) {
        homeLocationName.text = detailedWeather.title
        detailedWeather.consolidatedWeather?.firstOrNull()?.let { todayWeather ->
            homeLocationTemperature.text = todayWeather.theTemp?.roundToInt()?.toString()?.let {
                getString(R.string.home_location_temperature_text, it)
            }
            homeLocationTemperatureRangeLower.text = todayWeather.minTemp?.roundToInt()?.toString()?.let {
                getString(R.string.home_location_temperature_text, it)
            }
            homeLocationTemperatureRangeHigher.text = todayWeather.maxTemp?.roundToInt()?.toString()?.let {
                getString(R.string.home_location_temperature_text, it)
            }
            homeLocationWeatherState.text = todayWeather.weatherStateName
        }
    }
}
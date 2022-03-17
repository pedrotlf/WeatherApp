package br.com.pedrotlf.weatherapp.presentation.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrotlf.weatherapp.R
import br.com.pedrotlf.weatherapp.databinding.ItemForecastWeatherBinding
import br.com.pedrotlf.weatherapp.domain.model.ConsolidatedWeather
import kotlin.math.roundToInt

class ForecastWeatherItemVH(
    private val binding: ItemForecastWeatherBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(weather: ConsolidatedWeather){
        binding.apply {
            val day = weather.applicableDate
            val condition = weather.weatherStateName
            forecastItemDayAndCondition.text = root.context.getString(
                R.string.forecast_weather_item_day_and_condition,
                day,
                condition
            )

            val minTemp = weather.minTemp?.roundToInt()
            val maxTemp = weather.maxTemp?.roundToInt()
            forecastItemWeatherRange.text = root.context.getString(
                R.string.forecast_weather_item_weather_range,
                minTemp,
                maxTemp
            )
        }
    }

    companion object{
        fun create(parent: ViewGroup): ForecastWeatherItemVH{
            val view = ItemForecastWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return ForecastWeatherItemVH(view)
        }
    }
}
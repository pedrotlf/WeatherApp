package br.com.pedrotlf.weatherapp.presentation.forecast

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.pedrotlf.weatherapp.domain.model.ConsolidatedWeather

class ForecastWeatherListAdapter : ListAdapter<ConsolidatedWeather, ForecastWeatherItemVH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastWeatherItemVH {
        return ForecastWeatherItemVH.create(parent)
    }

    override fun onBindViewHolder(holder: ForecastWeatherItemVH, position: Int) {
        val weather = getItem(position)
        holder.bind(weather)
    }

    class DiffCallback: DiffUtil.ItemCallback<ConsolidatedWeather>(){
        override fun areItemsTheSame(
            oldItem: ConsolidatedWeather,
            newItem: ConsolidatedWeather
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ConsolidatedWeather,
            newItem: ConsolidatedWeather
        ): Boolean = oldItem == newItem
    }
}
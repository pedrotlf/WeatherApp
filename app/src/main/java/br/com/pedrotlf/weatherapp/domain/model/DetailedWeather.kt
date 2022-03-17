package br.com.pedrotlf.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class DetailedWeather(
    val consolidatedWeather: List<ConsolidatedWeather>?,
    val lattLong: String?,
    val sunRise: String?,
    val sunSet: String?,
    val time: String?,
    val title: String?,
    val woeid: Long?
)

@Parcelize
data class ConsolidatedWeather(
    val applicableDate: String?,
    val humidity: Int?,
    val id: Long?,
    val maxTemp: Double?,
    val minTemp: Double?,
    val predictability: Int?,
    val theTemp: Double?,
    val visibility: Double?,
    val weatherStateAbbr: String?,
    val weatherStateName: String?,
    val windDirection: Double?,
    val windDirectionCompass: String?,
    val windSpeed: Double?
): Parcelable
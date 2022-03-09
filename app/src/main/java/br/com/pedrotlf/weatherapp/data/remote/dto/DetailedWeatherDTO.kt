package br.com.pedrotlf.weatherapp.data.remote.dto

import br.com.pedrotlf.weatherapp.domain.model.ConsolidatedWeather
import br.com.pedrotlf.weatherapp.domain.model.DetailedWeather
import com.fasterxml.jackson.annotation.JsonProperty

data class DetailedWeatherDTO(
    @JsonProperty("consolidated_weather") val consolidatedWeather: List<ConsolidatedWeatherDTO>?,
    @JsonProperty("latt_long") val lattLong: String?,
    @JsonProperty("location_type") val locationType: String?,
    val parent: ParentDTO?,
    val sources: List<SourceDTO>?,
    @JsonProperty("sun_rise") val sunRise: String?,
    @JsonProperty("sun_set") val sunSet: String?,
    val time: String?,
    val timezone: String?,
    @JsonProperty("timezone_name") val timezoneName: String?,
    val title: String?,
    val woeid: Long?
)

fun DetailedWeatherDTO.toDetailedWeather() = DetailedWeather(
    consolidatedWeather?.map { it.toConsolidatedWeather() },
    lattLong,
    sunRise,
    sunSet,
    time,
    title,
    woeid
)

data class ConsolidatedWeatherDTO(
    @JsonProperty("air_pressure") val airPressure: Double?,
    @JsonProperty("applicable_date") val applicableDate: String?,
    val created: String?,
    val humidity: Int?,
    val id: Long?,
    @JsonProperty("max_temp") val maxTemp: Double?,
    @JsonProperty("min_temp") val minTemp: Double?,
    val predictability: Int?,
    @JsonProperty("the_temp") val theTemp: Double?,
    val visibility: Double?,
    @JsonProperty("weather_state_abbr") val weatherStateAbbr: String?,
    @JsonProperty("weather_state_name") val weatherStateName: String?,
    @JsonProperty("wind_direction") val windDirection: Double?,
    @JsonProperty("wind_direction_compass") val windDirectionCompass: String?,
    @JsonProperty("wind_speed") val windSpeed: Double?
)

fun ConsolidatedWeatherDTO.toConsolidatedWeather() = ConsolidatedWeather(
    applicableDate,
    humidity,
    id,
    maxTemp,
    minTemp,
    predictability,
    theTemp,
    visibility,
    weatherStateAbbr,
    weatherStateName,
    windDirection,
    windDirectionCompass,
    windSpeed
)

data class ParentDTO(
    @JsonProperty("latt_long") val lattLong: String?,
    @JsonProperty("location_type") val locationType: String?,
    val title: String?,
    val woeid: Long?
)

data class SourceDTO(
    @JsonProperty("crawl_rate") val crawlRate: Int?,
    val slug: String?,
    val title: String?,
    val url: String?
)

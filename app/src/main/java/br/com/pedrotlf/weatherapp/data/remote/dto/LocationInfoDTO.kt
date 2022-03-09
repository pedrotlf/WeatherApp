package br.com.pedrotlf.weatherapp.data.remote.dto

import br.com.pedrotlf.weatherapp.domain.model.LocationInfo
import com.fasterxml.jackson.annotation.JsonProperty

data class LocationInfoDTO(
    val distance: Int,
    @JsonProperty("latt_long") val lattLong: String,
    @JsonProperty("location_type") val locationType: String,
    val title: String,
    val woeid: Long
)

fun LocationInfoDTO.toLocationInfo() = LocationInfo(
    distance,
    locationType,
    title,
    woeid
)
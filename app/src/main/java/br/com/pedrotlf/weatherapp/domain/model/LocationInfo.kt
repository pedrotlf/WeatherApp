package br.com.pedrotlf.weatherapp.domain.model

data class LocationInfo(
    val distance: Int?,
    val locationType: String?,
    val title: String?,
    val woeid: Long?
)
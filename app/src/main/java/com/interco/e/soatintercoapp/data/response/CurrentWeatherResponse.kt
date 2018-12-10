package com.interco.e.soatintercoapp.data.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("Current")
    val current: CurrentWeatherEntry,
    val location: Location
)
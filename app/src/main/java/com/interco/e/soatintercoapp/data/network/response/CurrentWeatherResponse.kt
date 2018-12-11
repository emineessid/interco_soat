package com.interco.e.soatintercoapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.interco.e.soatintercoapp.data.db.entity.CurrentWeatherEntry
import com.interco.e.soatintercoapp.data.db.entity.Location

data class CurrentWeatherResponse(
    val location: Location,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry

)
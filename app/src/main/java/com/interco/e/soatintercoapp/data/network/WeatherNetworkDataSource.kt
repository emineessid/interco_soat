package com.interco.e.soatintercoapp.data.network

import androidx.lifecycle.LiveData
import com.interco.e.soatintercoapp.data.network.response.CurrentWeatherResponse

/**
 * Created by emine on 10/12/2018.
 */
interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    suspend fun feachCurrentWeather(location: String, LaguageCode: String)
}
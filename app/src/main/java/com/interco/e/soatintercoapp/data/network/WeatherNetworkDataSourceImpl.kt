package com.interco.e.soatintercoapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.interco.e.soatintercoapp.data.ApixuWeatherApiService
import com.interco.e.soatintercoapp.data.network.response.CurrentWeatherResponse

class WeatherNetworkDataSourceImpl(private val apixuWeatherApiService: ApixuWeatherApiService) :
    WeatherNetworkDataSource {
    private val _downloadedCurrentWeather: MutableLiveData<CurrentWeatherResponse> = MutableLiveData();
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather;

    override suspend fun feachCurrentWeather(location: String, laguageCode: String) {
        try {
            val fetchedCurrentDataWeather = apixuWeatherApiService.getCurrentWeather(location, laguageCode).await()
            _downloadedCurrentWeather.postValue(fetchedCurrentDataWeather)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", e.message)
        }
    }
}
package com.interco.e.soatintercoapp.data.repository

import androidx.lifecycle.LiveData
import com.interco.e.soatintercoapp.data.db.CurrentWeatherDao
import com.interco.e.soatintercoapp.data.db.unitLocalised.UnitSpecificCurrentWeatherEnty
import com.interco.e.soatintercoapp.data.network.WeatherNetworkDataSource
import com.interco.e.soatintercoapp.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            persistFeachedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEnty> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else
                currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFeachedCurrentWeather(feachedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(feachedWeather.currentWeatherEntry)
        }
    }

    private suspend fun initWeatherData() {
//yes always needed for now ! and the value will be always true
        if (isFeachNeeded(ZonedDateTime.now().minusHours(1))) {
            feachCurrentWeather()
        }
    }

    private suspend fun feachCurrentWeather() {

        weatherNetworkDataSource.feachCurrentWeather("Paris", Locale.getDefault().language)

    }

    private fun isFeachNeeded(lastFechTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFechTime.isBefore(thirtyMinutesAgo)
    }
}
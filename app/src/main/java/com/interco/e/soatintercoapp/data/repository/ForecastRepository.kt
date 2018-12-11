package com.interco.e.soatintercoapp.data.repository

import androidx.lifecycle.LiveData
import com.interco.e.soatintercoapp.data.db.unitLocalised.UnitSpecificCurrentWeatherEnty

/**
 * Created by emine on 10/12/2018.
 */
interface ForecastRepository {
    suspend fun  getCurrentWeather(metric : Boolean):LiveData< out UnitSpecificCurrentWeatherEnty>
}
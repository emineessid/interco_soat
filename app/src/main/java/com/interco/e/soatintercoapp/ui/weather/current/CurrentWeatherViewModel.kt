package com.interco.e.soatintercoapp.ui.weather.current

import androidx.lifecycle.ViewModel
import com.interco.e.soatintercoapp.data.repository.ForecastRepository
import com.interco.e.soatintercoapp.internal.UnitSystem
import com.interco.e.soatintercoapp.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository

) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}

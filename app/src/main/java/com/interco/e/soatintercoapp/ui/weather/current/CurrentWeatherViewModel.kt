package com.interco.e.soatintercoapp.ui.weather.current

import com.interco.e.soatintercoapp.data.provider.UnitProvider
import com.interco.e.soatintercoapp.data.repository.ForecastRepository
import com.interco.e.soatintercoapp.internal.UnitSystem
import com.interco.e.soatintercoapp.ui.base.SafeCallViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider

) : SafeCallViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()
    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred { forecastRepository.getCurrentWeather(isMetric) }


}

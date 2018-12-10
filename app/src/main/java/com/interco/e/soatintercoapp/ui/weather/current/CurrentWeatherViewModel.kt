package com.interco.e.soatintercoapp.ui.weather.current

import androidx.lifecycle.ViewModel
import com.interco.e.soatintercoapp.data.repository.ForcastREpository
import com.interco.e.soatintercoapp.internal.UnitSystem
import com.interco.e.soatintercoapp.internal.lazyDeffed

class CurrentWeatherViewModel(
    private val forcastREpository: ForcastREpository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC
    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeffed { forcastREpository.getCurrentWeather(isMetric) }


}

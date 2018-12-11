package com.interco.e.soatintercoapp.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.interco.e.soatintercoapp.data.provider.UnitProvider
import com.interco.e.soatintercoapp.data.repository.ForecastRepository

/**
 * Created by emine on 11/12/2018.
 */
@Suppress("UNCHECKED_CAST")
class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository,unitProvider) as T
    }
}
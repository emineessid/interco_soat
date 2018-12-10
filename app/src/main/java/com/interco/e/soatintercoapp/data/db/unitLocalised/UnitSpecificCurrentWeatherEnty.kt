package com.interco.e.soatintercoapp.data.db.unitLocalised

/**
 * Created by emine on 10/12/2018.
 */
interface UnitSpecificCurrentWeatherEnty {
    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}
package com.interco.e.soatintercoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interco.e.soatintercoapp.data.db.entity.CURRENT_WEATHER_FIXED_ID
import com.interco.e.soatintercoapp.data.db.entity.CurrentWeatherEntry
import com.interco.e.soatintercoapp.data.db.unitLocalised.ImperialCurrentWeatherEnty
import com.interco.e.soatintercoapp.data.db.unitLocalised.MetricCurrentWeatherEntry

/**
 * Created by emine on 10/12/2018.
 */

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_FIXED_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id = $CURRENT_WEATHER_FIXED_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEnty>
}
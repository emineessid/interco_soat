package com.interco.e.soatintercoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interco.e.soatintercoapp.data.db.entity.CurrentWeatherEntry
import com.interco.e.soatintercoapp.data.db.unitLocalised.ImperialCurrentWeatherEnty
import com.interco.e.soatintercoapp.data.db.unitLocalised.MetricCurrentWeatherEntry

/**
 * Created by emine on 10/12/2018.
 */
@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //The Full responce not a specific one !
    fun upsert(entry: CurrentWeatherEntry)

    @Query("select * from Current_weather where id =0") // CurrentWethzr ID
    fun getWeatherMetric(Currenw_WEATHER_FIXED_ID: String): LiveData<MetricCurrentWeatherEntry>

    @Query("select * from Current_weather where id =id =0")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEnty>
}
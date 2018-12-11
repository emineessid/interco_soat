package com.interco.e.soatintercoapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.interco.e.soatintercoapp.data.db.entity.CurrentWeatherEntry

/**
 * Created by emine on 10/12/2018.
 */
@Database(
    entities = [CurrentWeatherEntry::class]
    , version = 1
)
abstract class ForecastDatabase() : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object {
        //need companion to make it sigleton !

        @Volatile
        private var instance: ForecastDatabase? = null
        private val LOCK = Any() //Just a dummy object !

        //invoke is just to do not call ForecastDatabase.invoke () but just ForecastDatabase () ;)
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ForecastDatabase::class.java, "forecast_original.db"
            )
                .build()
    }
}

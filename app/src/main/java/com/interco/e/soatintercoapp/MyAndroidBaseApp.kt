package com.interco.e.soatintercoapp

import android.app.Application
import com.interco.e.soatintercoapp.data.ApixuWeatherApiService
import com.interco.e.soatintercoapp.data.db.ForecastDatabase
import com.interco.e.soatintercoapp.data.network.ConnectivityInterceptor
import com.interco.e.soatintercoapp.data.network.ConnectivityInterceptorImpl
import com.interco.e.soatintercoapp.data.network.WeatherNetworkDataSource
import com.interco.e.soatintercoapp.data.network.WeatherNetworkDataSourceImpl
import com.interco.e.soatintercoapp.data.repository.ForcastREpository
import com.interco.e.soatintercoapp.data.repository.ForcastRepositoryImpl
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Created by emine on 10/12/2018.
 */

class MyAndroidBaseApp : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidModule(this@MyAndroidBaseApp))
        //INJECT DATABASE !
        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        //INJECT Connectivity !
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        //Pass connectivity to ApixuWeatherApiService
        bind() from singleton { ApixuWeatherApiService(instance()) }

        //Repesotery inject !
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForcastREpository>() with singleton { ForcastRepositoryImpl(instance(), instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
package com.interco.e.soatintercoapp

import android.app.Application
import com.bumptech.glide.annotation.GlideModule
import com.interco.e.soatintercoapp.data.ApixuWeatherApiService
import com.interco.e.soatintercoapp.data.db.ForecastDatabase
import com.interco.e.soatintercoapp.data.network.ConnectivityInterceptor
import com.interco.e.soatintercoapp.data.network.ConnectivityInterceptorImpl
import com.interco.e.soatintercoapp.data.network.WeatherNetworkDataSource
import com.interco.e.soatintercoapp.data.network.WeatherNetworkDataSourceImpl
import com.interco.e.soatintercoapp.data.repository.ForecastRepository
import com.interco.e.soatintercoapp.data.repository.ForecastRepositoryImpl
import com.interco.e.soatintercoapp.ui.weather.current.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by emine on 10/12/2018.
 */
@GlideModule
class MyAndroidBaseApp : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidModule(this@MyAndroidBaseApp))


        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
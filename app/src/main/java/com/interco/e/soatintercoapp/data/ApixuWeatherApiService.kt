package com.interco.e.soatintercoapp.data

import com.interco.e.soatintercoapp.data.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by emine on 10/12/2018.
 */

const val API_KEY = "89e8bd89085b41b7a4b142029180210"

//http://api.apixu.com/v1/current.json?key=89e8bd89085b41b7a4b142029180210&q=London&lang=en

interface ApixuWeatherApiService {
    @GET("current.json")
    fun getCetcurrentWeather(

        @Query("q") location: String,
        @Query("lang") language: String = "en"

    ): Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(): ApixuWeatherApiService {

            val requestInterceptor = Interceptor { chain ->
                val url = chain.request().url().newBuilder().addQueryParameter("key", API_KEY).build()
                val request = chain.request().newBuilder().url(url).build()
                return@Interceptor chain.proceed(request)

            }
            val okHttpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor).build()
            return Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create()).build().create(ApixuWeatherApiService::class.java)

        }
    }
}
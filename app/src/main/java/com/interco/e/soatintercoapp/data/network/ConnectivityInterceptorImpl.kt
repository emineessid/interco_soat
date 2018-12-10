package com.interco.e.soatintercoapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {

    private val appContext = context.applicationContext
    override fun isUserHaveInternet(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected;
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isUserHaveInternet())
            throw NoInternetExeption()
        else
            return chain.proceed(chain.request())
    }
}

class NoInternetExeption : Throwable() {
    override val message: String?
        get() = "You dont have internet Connection !"
}

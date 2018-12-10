package com.interco.e.soatintercoapp.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.interco.e.soatintercoapp.R
import com.interco.e.soatintercoapp.data.ApixuWeatherApiService
import com.interco.e.soatintercoapp.data.network.ConnectivityInterceptorImpl
import com.interco.e.soatintercoapp.data.network.WeatherNetworkDataSourceImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)


        val apiSErvice = ApixuWeatherApiService(ConnectivityInterceptorImpl(activity!!.applicationContext));

        val weatherNetworkDataSourceImpl = WeatherNetworkDataSourceImpl(apiSErvice)

        weatherNetworkDataSourceImpl.downloadedCurrentWeather.observe(this, Observer {
            textView_ok.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            weatherNetworkDataSourceImpl.feachCurrentWeather("Paris","en")
        }


    }

}

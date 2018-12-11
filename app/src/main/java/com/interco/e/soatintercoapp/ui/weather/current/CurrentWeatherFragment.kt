package com.interco.e.soatintercoapp.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.interco.e.soatintercoapp.R
import com.interco.e.soatintercoapp.ui.base.ScopedBaseFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class CurrentWeatherFragment : ScopedBaseFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val CurrentWeatherViewModelFactory: CurrentWeatherViewModelFactory  by instance() // get last injected instance !

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, CurrentWeatherViewModelFactory).get(CurrentWeatherViewModel::class.java)

        bindUi()


    }

    private fun bindUi() = launch {
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) return@Observer
            textView_ok.text = it.toString()!!
        })
    }

}

package com.interco.e.soatintercoapp.data.provider

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.interco.e.soatintercoapp.internal.UnitSystem

class UnitProviderImpl(context: Context) : UnitProvider {
    private val appContext = context.applicationContext
    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getUnitSystem(): UnitSystem {
        val seletdName = preference.getString("UNIT_SYSTEM", UnitSystem.METRIC.name)
        return UnitSystem.valueOf(seletdName!!)
    }
}
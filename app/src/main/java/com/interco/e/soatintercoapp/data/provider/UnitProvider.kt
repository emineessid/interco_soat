package com.interco.e.soatintercoapp.data.provider

import com.interco.e.soatintercoapp.internal.UnitSystem

/**
 * Created by emine on 11/12/2018.
 */
interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}
package com.interco.e.soatintercoapp.internal

import kotlinx.coroutines.*

/**
 * Created by emine on 10/12/2018.
 */

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}
package com.interco.e.soatintercoapp.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.io.IOException

/**
 * Created by emine on 11/12/2018.
 */
open class SafeCallViewModel : ViewModel() {

    fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
        return lazy {
            GlobalScope.async(start = CoroutineStart.LAZY) {
                block.invoke(this)
            }
        }
    }

    fun <T : Any> safeLazyDeferredCall(
        block: suspend CoroutineScope.() -> T,
        errorMessage: String
    ): Lazy<Deferred<T>>? {
        try {
            return lazy {
                GlobalScope.async(start = CoroutineStart.LAZY) {
                    block.invoke(this)
                }
            }
        } catch (e: Exception) {
            ApiResult.Error(IOException(errorMessage, e))
        }

        return null

    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> ApiResult<T>, errorMessage: String): ApiResult<T> = try {
        call.invoke()
    } catch (e: Exception) {
        ApiResult.Error(IOException(errorMessage, e))
    }


    open  class ApiResult<out T : Any> {
        data class Success<out T : Any>(val data: T) : ApiResult<T>()
        data class Error(val exception: Exception) : ApiResult<Nothing>()
    }
}
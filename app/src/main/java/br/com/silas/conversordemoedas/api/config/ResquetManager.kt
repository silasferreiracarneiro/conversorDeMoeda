package br.com.silas.conversordemoedas.api.config

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Synchronized
suspend fun <T> doResquest(requestFunction: suspend () -> T): ResultApi<T> {
    return withContext(Dispatchers.IO) {
        try {
            ResultApi.createSucess(
                requestFunction()
            )
        } catch (exception: Throwable) {
            print(exception)
            ResultApi.createError<T>(
                exception
            )
        }
    }
}
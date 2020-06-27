package br.com.silas.conversordemoedas.api

import br.com.silas.conversordemoedas.api.model.MoedaResponse
import br.com.silas.conversordemoedas.api.model.TaxaCambioResponse
import br.com.silas.conversordemoedas.utils.Constants.KEY_ACCESS
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/list")
    fun getMoedas(@Query("access_key") accessKey: String = KEY_ACCESS): Deferred<MoedaResponse>

    @GET("/live")
    fun getTaxaCambio(@Query("access_key") accessKey: String = KEY_ACCESS): Deferred<TaxaCambioResponse>
}
package br.com.silas.conversordemoedas.repository

import br.com.silas.conversordemoedas.api.Api
import br.com.silas.conversordemoedas.api.config.ResultApi
import br.com.silas.conversordemoedas.api.config.doResquest
import br.com.silas.conversordemoedas.api.model.TaxaCambioResponse

class TaxaCambioRepository(private val api: Api) {

    suspend fun getTaxaCambio() : ResultApi<TaxaCambioResponse> =
        doResquest {
            api.getTaxaCambio().await()
        }
}
package br.com.silas.conversordemoedas.repository

import br.com.silas.conversordemoedas.api.Api
import br.com.silas.conversordemoedas.api.config.ResultApi
import br.com.silas.conversordemoedas.api.config.doResquest
import br.com.silas.conversordemoedas.api.model.MoedaResponse

class ListagemMoedaRepository(val api: Api) {

    suspend fun getMoedas(): ResultApi<MoedaResponse> {
        return doResquest {
            api.getMoedas().await()
        }
    }
}
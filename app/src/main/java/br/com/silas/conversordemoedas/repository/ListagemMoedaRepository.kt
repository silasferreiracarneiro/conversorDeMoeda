package br.com.silas.conversordemoedas.repository

import br.com.silas.conversordemoedas.data.network.Api
import br.com.silas.conversordemoedas.data.network.config.ResultApi
import br.com.silas.conversordemoedas.data.network.config.RetrofitConfig
import br.com.silas.conversordemoedas.data.network.config.doResquest
import br.com.silas.conversordemoedas.data.network.model.MoedaResponse

class ListagemMoedaRepository(val api: Api = RetrofitConfig().api) {

    suspend fun getMoedas(): ResultApi<MoedaResponse> {
        return doResquest {
            api.getMoedas().await()
        }
    }
}
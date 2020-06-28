package br.com.silas.conversordemoedas.repository

import android.app.Application
import br.com.silas.conversordemoedas.data.db.AppDatabase
import br.com.silas.conversordemoedas.data.network.Api
import br.com.silas.conversordemoedas.data.network.config.ResultApi
import br.com.silas.conversordemoedas.data.network.config.doResquest
import br.com.silas.conversordemoedas.data.network.model.TaxaCambio
import br.com.silas.conversordemoedas.data.network.model.TaxaCambioResponse

class TaxaCambioRepository(private val api: Api) {

    suspend fun obterTaxaDeCambioOnline() : ResultApi<TaxaCambioResponse> =
        doResquest {
            api.getTaxaCambio().await()
        }

    suspend fun obterTaxaDeCambioOffline(): ResultApi<List<TaxaCambio>>? {
//        val dao = AppDatabase.invoke(application).apiDaoLocal()
//        return doResquest {
//            dao.getTaxaCambio().await()
//        }
        return null
    }

    suspend fun insereTaxaCambio() {

    }
}
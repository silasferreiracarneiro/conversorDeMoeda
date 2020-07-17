package br.com.silas.conversordemoedas.repository

import br.com.silas.conversordemoedas.data.db.DatabaseDao
import br.com.silas.conversordemoedas.data.network.Api
import br.com.silas.conversordemoedas.data.network.config.ResultApi
import br.com.silas.conversordemoedas.data.network.config.doResquest
import br.com.silas.conversordemoedas.model.TaxaCambio
import br.com.silas.conversordemoedas.data.network.model.TaxaCambioResponse
import br.com.silas.conversordemoedas.data.prefs.SharedPreferencesManager

class TaxaCambioRepository(private val api: Api, private val database: DatabaseDao, private val prefs: SharedPreferencesManager) {

    suspend fun obterTaxaDeCambioOnline() : ResultApi<TaxaCambioResponse> =
        doResquest {
            api.getTaxaCambio().await()
        }

    suspend fun obterTaxaDeCambioOffline(): List<TaxaCambio> = database.getTaxaCambio()

    suspend fun insereTaxaCambio(taxa: TaxaCambio) = database.insertTaxaCambio(taxa)

    suspend fun isOnline(): Boolean = prefs.getSeEhParaUsarDadosMoveis()
}
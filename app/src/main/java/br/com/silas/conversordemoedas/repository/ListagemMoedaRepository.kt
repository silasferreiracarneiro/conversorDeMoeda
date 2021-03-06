package br.com.silas.conversordemoedas.repository

import br.com.silas.conversordemoedas.data.db.DatabaseDao
import br.com.silas.conversordemoedas.data.network.Api
import br.com.silas.conversordemoedas.data.network.config.ResultApi
import br.com.silas.conversordemoedas.data.network.config.doResquest
import br.com.silas.conversordemoedas.data.network.model.MoedaResponse
import br.com.silas.conversordemoedas.data.prefs.SharedPreferencesManager
import br.com.silas.conversordemoedas.model.Moeda

class ListagemMoedaRepository(private val api: Api, private val database: DatabaseDao, private val prefs: SharedPreferencesManager) {

    suspend fun getMoedasOnline(): ResultApi<MoedaResponse> =
        doResquest {
            api.getMoedas().await()
        }

    suspend fun saveMoeda(moeda: Moeda) = database.insertMoeda(moeda)

    suspend fun isOnline(): Boolean = prefs.getSeEhParaUsarDadosMoveis()

    suspend fun getMoedasOffline() = database.getMoedas()
}
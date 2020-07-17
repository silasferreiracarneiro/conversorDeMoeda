package br.com.silas.conversordemoedas.usecase

import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.repository.ListagemMoedaRepository

class ListagemMoedaUseCase(private val respository: ListagemMoedaRepository) {

    suspend fun getMoedasOnline() = respository.getMoedasOnline()

    suspend fun saveMoeda(moeda: Moeda) = respository.saveMoeda(moeda)

    suspend fun isOnline() = respository.isOnline()

    suspend fun getMoedasOffline() = respository.getMoedasOffline()
}
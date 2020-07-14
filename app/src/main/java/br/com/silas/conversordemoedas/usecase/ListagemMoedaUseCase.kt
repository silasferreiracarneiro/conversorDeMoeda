package br.com.silas.conversordemoedas.usecase

import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.repository.ListagemMoedaRepository

class ListagemMoedaUseCase(private val respository: ListagemMoedaRepository) {
    suspend fun getMoedas() = respository.getMoedas()

    suspend fun saveMoeda(moeda: Moeda) = respository.saveMoeda(moeda)
}
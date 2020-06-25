package br.com.silas.conversordemoedas.usecase

import br.com.silas.conversordemoedas.repository.ListagemMoedaRepository

class ListagemMoedaUseCase(val respository: ListagemMoedaRepository) {
    suspend fun getMoedas() = respository.getMoedas()
}
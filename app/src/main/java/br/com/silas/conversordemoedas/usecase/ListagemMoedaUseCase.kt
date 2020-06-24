package br.com.silas.conversordemoedas.usecase

import br.com.silas.conversordemoedas.repository.ListagemMoedaRepsoitory

class ListagemMoedaUseCase(val respository: ListagemMoedaRepsoitory) {
    suspend fun getMoedas() = respository.getMoedas()
}
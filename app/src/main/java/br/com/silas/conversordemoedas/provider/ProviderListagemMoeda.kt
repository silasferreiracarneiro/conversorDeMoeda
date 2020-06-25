package br.com.silas.conversordemoedas.provider

import br.com.silas.conversordemoedas.repository.ListagemMoedaRepository
import br.com.silas.conversordemoedas.usecase.ListagemMoedaUseCase

fun providerListagemMoedaReposity(): ListagemMoedaRepository {
    return ListagemMoedaRepository(providerApi())
}

@JvmOverloads
fun providerListagemMoedaUseCase(): ListagemMoedaUseCase {
    return ListagemMoedaUseCase(providerListagemMoedaReposity())
}
package br.com.silas.conversordemoedas.provider

import br.com.silas.conversordemoedas.repository.ListagemMoedaRepsoitory
import br.com.silas.conversordemoedas.usecase.ListagemMoedaUseCase

fun providerListagemMoedaReposity(): ListagemMoedaRepsoitory {
    return ListagemMoedaRepsoitory(providerApi())
}

@JvmOverloads
fun providerListagemMoedaUseCase(): ListagemMoedaUseCase {
    return ListagemMoedaUseCase(providerListagemMoedaReposity())
}
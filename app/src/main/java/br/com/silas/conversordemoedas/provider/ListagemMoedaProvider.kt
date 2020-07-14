package br.com.silas.conversordemoedas.provider

import br.com.silas.conversordemoedas.repository.ListagemMoedaRepository
import br.com.silas.conversordemoedas.usecase.ListagemMoedaUseCase

fun providerListagemMoedaRepository() =
    ListagemMoedaRepository(providerApi())

@JvmOverloads
fun providerListagemMoedaUsecase() =
    ListagemMoedaUseCase(providerListagemMoedaRepository())
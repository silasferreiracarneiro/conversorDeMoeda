package br.com.silas.conversordemoedas.provider

import android.content.Context
import br.com.silas.conversordemoedas.repository.ListagemMoedaRepository
import br.com.silas.conversordemoedas.usecase.ListagemMoedaUseCase

fun providerListagemMoedaRepository(context: Context) =
    ListagemMoedaRepository(providerApi(), providerRoom(context), providerSharedPreferencesManager(context))

@JvmOverloads
fun providerListagemMoedaUsecase(context: Context) =
    ListagemMoedaUseCase(providerListagemMoedaRepository(context))
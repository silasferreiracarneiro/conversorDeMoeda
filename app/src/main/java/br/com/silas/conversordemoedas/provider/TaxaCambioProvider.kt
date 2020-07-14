package br.com.silas.conversordemoedas.provider

import android.content.Context
import br.com.silas.conversordemoedas.repository.TaxaCambioRepository
import br.com.silas.conversordemoedas.usecase.ConversaoMoedaUseCase

fun providerTaxaCambioRepository(context: Context) =
    TaxaCambioRepository(providerApi(), providerRoom(context))

fun providerTaxaCambioUsecase(context: Context) = ConversaoMoedaUseCase(providerTaxaCambioRepository(context))
package br.com.silas.conversordemoedas.provider

import android.content.Context
import br.com.silas.conversordemoedas.repository.MainRepository
import br.com.silas.conversordemoedas.usecase.MainUseCase

fun providerMainRepository(context: Context) = MainRepository(providerSharedPreferencesManager(context))

fun providerMainUseCase(context: Context) = MainUseCase(providerMainRepository(context))
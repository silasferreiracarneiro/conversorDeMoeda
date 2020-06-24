package br.com.silas.conversordemoedas.provider

import br.com.silas.conversordemoedas.api.Api
import br.com.silas.conversordemoedas.api.config.RetrofitConfig

fun providerApi(): Api = RetrofitConfig().api
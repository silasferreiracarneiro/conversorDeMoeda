package br.com.silas.conversordemoedas.provider

import br.com.silas.conversordemoedas.data.network.Api
import br.com.silas.conversordemoedas.data.network.config.RetrofitConfig

fun providerApi(): Api = RetrofitConfig().api
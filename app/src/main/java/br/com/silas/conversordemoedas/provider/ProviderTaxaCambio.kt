package br.com.silas.conversordemoedas.provider

import br.com.silas.conversordemoedas.repository.TaxaCambioRepository

fun providerTaxaCambioRepository(): TaxaCambioRepository {
    return TaxaCambioRepository(providerApi())
}
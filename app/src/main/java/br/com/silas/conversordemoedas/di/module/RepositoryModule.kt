package br.com.silas.conversordemoedas.di.module

import br.com.silas.conversordemoedas.di.PerActivity
import br.com.silas.conversordemoedas.repository.ListagemMoedaRepository
import br.com.silas.conversordemoedas.repository.TaxaCambioRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @PerActivity
    fun providerListagemMoedaRepository() = ListagemMoedaRepository()

    @Provides
    @PerActivity
    fun providerTaxaCambioRepository() = TaxaCambioRepository()
}
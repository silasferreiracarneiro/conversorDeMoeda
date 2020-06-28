package br.com.silas.conversordemoedas.di.module

import br.com.silas.conversordemoedas.di.PerActivity
import br.com.silas.conversordemoedas.repository.ListagemMoedaRepository
import br.com.silas.conversordemoedas.repository.TaxaCambioRepository
import br.com.silas.conversordemoedas.usecase.ConversaoMoedaUseCase
import br.com.silas.conversordemoedas.usecase.ListagemMoedaUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    @PerActivity
    fun proviverConversaoMoedaUsecase(repository: TaxaCambioRepository) = ConversaoMoedaUseCase(repository)

    @Provides
    @PerActivity
    fun providerListagemMoedaUsecase(repository: ListagemMoedaRepository) = ListagemMoedaUseCase(repository)
}
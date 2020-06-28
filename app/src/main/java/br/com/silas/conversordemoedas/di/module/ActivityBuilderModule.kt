package br.com.silas.conversordemoedas.di.module

import br.com.silas.conversordemoedas.ui.conversaoMoeda.ConversaoMoedaFragment
import br.com.silas.conversordemoedas.ui.listagemMoeda.ListagemMoedaFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectListagemMoedaFragemnt(): ListagemMoedaFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectConversaoMoedaFragemnt(): ConversaoMoedaFragment
}
package br.com.silas.conversordemoedas.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.silas.conversordemoedas.di.ViewModelKey
import br.com.silas.conversordemoedas.viewmodel.ConversaoMoedaViewModel
import br.com.silas.conversordemoedas.viewmodel.ListagemMoedaViewModel
import br.com.silas.conversordemoedas.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListagemMoedaViewModel::class)
    abstract fun bindListagemMoedaViewModel(listagemMoedaViewModel: ListagemMoedaViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConversaoMoedaViewModel::class)
    abstract fun bindConversaoMoedaViewModel(conversaoMoedaViewModel: ConversaoMoedaViewModel) : ViewModel
}
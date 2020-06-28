package br.com.silas.conversordemoedas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.silas.conversordemoedas.usecase.ConversaoMoedaUseCase
import br.com.silas.conversordemoedas.usecase.ListagemMoedaUseCase
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

class ViewModelFactory
@Inject constructor(val viewModelMap : Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
    :ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelMap[modelClass]?.get() as T
    }

}
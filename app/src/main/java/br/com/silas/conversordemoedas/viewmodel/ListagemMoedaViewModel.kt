package br.com.silas.conversordemoedas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silas.conversordemoedas.api.config.ResultApi
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.provider.providerListagemMoedaUseCase
import br.com.silas.conversordemoedas.usecase.ListagemMoedaUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListagemMoedaViewModel(val usecase: ListagemMoedaUseCase = providerListagemMoedaUseCase()) : ViewModel() {

    var moedaEscolhidaDe = MutableLiveData<Moeda>()
    var moedaEscolhidaPara = MutableLiveData<Moeda>()

    fun getMoedas() {
        GlobalScope.launch {
            val moedas = usecase.getMoedas()
            afterCall(
                moedas
            )
        }
    }

    private fun afterCall(moedas: ResultApi<Moeda>) {
        when(moedas.isSucess()) {
            true -> null
            false -> null
        }
    }
}
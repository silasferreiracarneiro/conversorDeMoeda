package br.com.silas.conversordemoedas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silas.conversordemoedas.api.config.ResultApi
import br.com.silas.conversordemoedas.api.model.MoedaResponse
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.provider.providerListagemMoedaUseCase
import br.com.silas.conversordemoedas.usecase.ListagemMoedaUseCase
import br.com.silas.conversordemoedas.utils.Constants.CONVERTER_DE
import br.com.silas.conversordemoedas.utils.Constants.CONVERTER_PARA
import br.com.silas.conversordemoedas.viewmodel.states.listaMoeda.ListagemMoedaEvent
import br.com.silas.conversordemoedas.viewmodel.states.listaMoeda.ListagemMoedaState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListagemMoedaViewModel(val usecase: ListagemMoedaUseCase = providerListagemMoedaUseCase()) : ViewModel() {

    private var event = MutableLiveData<ListagemMoedaEvent>()
    private var state = MutableLiveData<ListagemMoedaState>()

    var moedaEscolhidaDe = MutableLiveData<Moeda>()
    var moedaEscolhidaPara = MutableLiveData<Moeda>()

    var viewEvent = event
    var viewState = state

    fun getMoedas() {
        GlobalScope.launch {
            val moedas = usecase.getMoedas()
            afterCall(
                moedas
            )
        }
    }

    private fun afterCall(moedas: ResultApi<MoedaResponse>) {
        when(moedas.isSucess()) {
            true -> state.postValue(ListagemMoedaState.SucessCallApi(moedas.value))
            false -> state.postValue(ListagemMoedaState.ErrorCallApi(moedas.error))
        }
    }

    fun setMoedaSelecionada(
        moeda: Moeda,
        quemConverte: Int
    ) {
        when (quemConverte) {
            CONVERTER_DE -> this.moedaEscolhidaDe.postValue(moeda)
            CONVERTER_PARA -> this.moedaEscolhidaPara.postValue(moeda)
        }
    }
}
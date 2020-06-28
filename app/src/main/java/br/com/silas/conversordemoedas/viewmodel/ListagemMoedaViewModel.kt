package br.com.silas.conversordemoedas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silas.conversordemoedas.data.network.config.ResultApi
import br.com.silas.conversordemoedas.data.network.model.MoedaResponse
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.usecase.ListagemMoedaUseCase
import br.com.silas.conversordemoedas.utils.Constants.CONVERTER_DE
import br.com.silas.conversordemoedas.utils.Constants.CONVERTER_PARA
import br.com.silas.conversordemoedas.viewmodel.states.listaMoeda.ListagemMoedaState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListagemMoedaViewModel @Inject constructor(val usecase: ListagemMoedaUseCase) : ViewModel() {

    private var state = MutableLiveData<ListagemMoedaState>()

    var moedaEscolhidaDe = MutableLiveData<Moeda?>()
    var moedaEscolhidaPara = MutableLiveData<Moeda?>()

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
            CONVERTER_DE -> moedaEscolhidaDe.postValue(moeda)
            CONVERTER_PARA -> moedaEscolhidaPara.postValue(moeda)
        }
    }

    fun inverteValoresDoBotao() {
        val moedaDe = moedaEscolhidaDe
        val moedaPara = moedaEscolhidaPara
        moedaEscolhidaDe.postValue(moedaPara.value)
        moedaEscolhidaPara.postValue(moedaDe.value)
    }
}
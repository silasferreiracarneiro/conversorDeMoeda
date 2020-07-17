package br.com.silas.conversordemoedas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.silas.conversordemoedas.data.network.config.ResultApi
import br.com.silas.conversordemoedas.data.network.model.MoedaResponse
import br.com.silas.conversordemoedas.data.network.model.converteMapParaListaDeMoeda
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.provider.providerListagemMoedaUsecase
import br.com.silas.conversordemoedas.usecase.ListagemMoedaUseCase
import br.com.silas.conversordemoedas.utils.Constants.CONVERTER_DE
import br.com.silas.conversordemoedas.utils.Constants.CONVERTER_PARA
import br.com.silas.conversordemoedas.viewmodel.states.listaMoeda.ListagemMoedaState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListagemMoedaViewModel(application: Application) : AndroidViewModel(application) {

    private val usecase: ListagemMoedaUseCase = providerListagemMoedaUsecase(application.applicationContext)

    private var state = MutableLiveData<ListagemMoedaState>()

    var moedaEscolhidaDe = MutableLiveData<Moeda?>()
    var moedaEscolhidaPara = MutableLiveData<Moeda?>()

    var viewState = state

    fun getMoedas() {
        GlobalScope.launch {
            if(usecase.isOnline()) {
                val moedas = usecase.getMoedasOnline()
                afterCall(
                    moedas
                )
            } else {
                val moedas = usecase.getMoedasOffline()
                afterSearchOffiline(
                    moedas
                )
            }
        }
    }

    private fun afterSearchOffiline(moedas: List<Moeda>) {
        state.postValue(ListagemMoedaState.SucessCallApi(moedas))
    }

    private fun afterCall(moedas: ResultApi<MoedaResponse>) {
        when(moedas.isSucess()) {
            true -> sucessoCallApi(moedas.value)
            false -> state.postValue(ListagemMoedaState.ErrorCallApi(moedas.error))
        }
    }

    private fun sucessoCallApi(response: MoedaResponse?) {
        val result = response?.converteMapParaListaDeMoeda()
        result?.let {
            salvaListaDeMoedas(it)
            state.postValue(ListagemMoedaState.SucessCallApi(it))
        }
    }

    private fun salvaListaDeMoedas(it: List<Moeda>) {
        it.forEach { moeda ->
            GlobalScope.launch {
                usecase.saveMoeda(moeda)
            }
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
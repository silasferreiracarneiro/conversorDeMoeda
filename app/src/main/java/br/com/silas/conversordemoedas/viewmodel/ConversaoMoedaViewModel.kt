package br.com.silas.conversordemoedas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.silas.conversordemoedas.data.network.config.ResultApi
import br.com.silas.conversordemoedas.data.network.model.TaxaCambioResponse
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.provider.providerTaxaCambioUsecase
import br.com.silas.conversordemoedas.usecase.ConversaoMoedaUseCase
import br.com.silas.conversordemoedas.viewmodel.states.conversaoMoeda.ConversaoMoedaState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigDecimal

class ConversaoMoedaViewModel(application: Application) : AndroidViewModel(application) {

    private val usecase: ConversaoMoedaUseCase = providerTaxaCambioUsecase(application.applicationContext)

    private var state = MutableLiveData<ConversaoMoedaState>()

    var viewState = state

    fun converte(isOnline: Boolean, sigla: String, nome: String, valor: BigDecimal) {
        when (isOnline) {
            true -> conversaoMoedaOnline(sigla, nome, valor)
            false -> conversaoMoedaOffline(sigla, nome, valor)
        }
    }

    private fun conversaoMoedaOffline(sigla: String, nome: String, valor: BigDecimal) {
        GlobalScope.launch {
            val result = usecase.getTaxaCambioOffline()
        }
    }

    private fun conversaoMoedaOnline(sigla: String, nome: String, valor: BigDecimal) {
        GlobalScope.launch {
            val result = usecase.getTaxaCambioOnline()
            afterCallOnline(
                result
            )
        }
    }

    private fun afterCallOnline(result: ResultApi<TaxaCambioResponse>) {
        when(result.isSucess()){
            false -> state.postValue(ConversaoMoedaState.ErroNaChamadaDaApi)
            else -> state.postValue(ConversaoMoedaState.SucessoNaChamadaDaApi(result = usecase.getConversaoCambio()))
        }
    }

    fun validacaoDasMoedasSelecionadas(moedaDe: Moeda?, moedaPara: Moeda?) {
        val resultValidacaoModedaDe = usecase.validaMoeda(moedaDe)
        val resultValidacaoModedaPara = usecase.validaMoeda(moedaPara)

        if (!resultValidacaoModedaDe && !resultValidacaoModedaPara)
            state.postValue(ConversaoMoedaState.SucessoNaValidacaoDasMoedas)
    }

    fun validaValorSelecionado(valor: BigDecimal) {
        val validacaoValor = usecase.validaValor(valor)
        afterValidacao(
            validacaoValor
        )
    }

    private fun afterValidacao(validacaoValor: Boolean) {
        when (validacaoValor) {
            false -> state.postValue(ConversaoMoedaState.SucessoValidacaoValor)
            true -> state.postValue(ConversaoMoedaState.ErroValidacaoValor)
        }
    }
}
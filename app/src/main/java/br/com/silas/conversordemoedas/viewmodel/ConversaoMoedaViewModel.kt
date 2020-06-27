package br.com.silas.conversordemoedas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.provider.providerConversaoMoedaUseCase
import br.com.silas.conversordemoedas.usecase.ConversaoMoedaUseCase
import br.com.silas.conversordemoedas.viewmodel.states.conversaoMoeda.ConversaoMoedaState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigDecimal

class ConversaoMoedaViewModel(private val usecase: ConversaoMoedaUseCase = providerConversaoMoedaUseCase())
    : ViewModel() {

    private var state = MutableLiveData<ConversaoMoedaState>()

    var viewState = state

    fun converte(sigla: String, nome: String, valor: BigDecimal) {
        GlobalScope.launch {
            val result = usecase.converteMoeda(sigla, nome, valor)
            afterCall(
                result
            )
        }
    }

    private fun afterCall(result: Double?) {
        when(result){
            null -> state.postValue(ConversaoMoedaState.ErroNaChamadaDaApi)
            else -> state.postValue(ConversaoMoedaState.SucessoNaChamadaDaApi(result = 0.0))
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
package br.com.silas.conversordemoedas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.silas.conversordemoedas.data.network.config.ResultApi
import br.com.silas.conversordemoedas.data.network.model.TaxaCambioResponse
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.model.TaxaCambio
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

    fun converte(siglaPara: String, valor: BigDecimal) {
        GlobalScope.launch {
            if(usecase.isOnline()) {
                conversaoMoedaOnline(siglaPara, valor)
            } else {
                conversaoMoedaOffline(siglaPara, valor)
            }
        }
    }

    private fun conversaoMoedaOffline(siglaPara: String, valor: BigDecimal) {
        GlobalScope.launch {
            val listaDeTaxa  = usecase.getTaxaCambioOffline()
            val result = usecase.conversaoMoedaOffline(siglaPara, valor, listaDeTaxa)
            state.postValue(ConversaoMoedaState.SucessoNaConversaoDaMoeda(result))
        }
    }

    private fun conversaoMoedaOnline(siglaPara: String, valor: BigDecimal) {
        GlobalScope.launch {
            val result = usecase.getTaxaCambioOnline()
            afterCallOnline(
                result,
                siglaPara,
                valor
            )
        }
    }

    private fun afterCallOnline(
        result: ResultApi<TaxaCambioResponse>,
        siglaPara: String,
        valor: BigDecimal
    ) {
        when(result.isSucess()){
            false -> state.postValue(ConversaoMoedaState.ErroNaChamadaDaApi)
            else -> sucessCallApi(result.value, siglaPara, valor)
        }
    }

    private fun sucessCallApi(
        result: TaxaCambioResponse?,
        siglaPara: String,
        valor: BigDecimal
    ) {
        val lista = result?.converteMapParaListaDeTaxaDeCambio()
        lista?.let {
            salvaListaDeTaxas(it)
            state.postValue(ConversaoMoedaState.SucessoNaConversaoDaMoeda(
                usecase.conversaoMoedaOnline(
                    it,
                    siglaPara,
                    valor
                )))
        }
    }

    private fun salvaListaDeTaxas(it: List<TaxaCambio>) {
        it.forEach { taxa ->
            GlobalScope.launch {
                usecase.insertTaxaCambio(taxa)
            }
        }
    }

    fun validacaoDasMoedasSelecionadas(moedaDe: Moeda?, moedaPara: Moeda?) {
        val resultValidacaoModedaDe = usecase.validaMoeda(moedaDe)
        val resultValidacaoModedaPara = usecase.validaMoeda(moedaPara)

        if (!resultValidacaoModedaDe && !resultValidacaoModedaPara)
            state.postValue(ConversaoMoedaState.SucessoNaValidacaoDasMoedas)
    }

    fun validaValorSelecionado(valor: BigDecimal) {
        when (usecase.validaValor(valor)) {
            false -> state.postValue(ConversaoMoedaState.SucessoValidacaoValor)
            true -> state.postValue(ConversaoMoedaState.ErroValidacaoValor)
        }
    }
}
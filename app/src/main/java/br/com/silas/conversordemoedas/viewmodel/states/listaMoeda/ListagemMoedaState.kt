package br.com.silas.conversordemoedas.viewmodel.states.listaMoeda

import br.com.silas.conversordemoedas.data.network.model.MoedaResponse

open class ListagemMoedaState {
    data class SucessCallApi(val value: MoedaResponse?): ListagemMoedaState()
    data class ErrorCallApi(val error: Throwable?) : ListagemMoedaState()
}
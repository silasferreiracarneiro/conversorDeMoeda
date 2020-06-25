package br.com.silas.conversordemoedas.viewmodel.states.listaMoeda

import br.com.silas.conversordemoedas.api.model.MoedaResponse

open class ListagemMoedaState {
    data class SucessCallApi(val value: MoedaResponse?): ListagemMoedaState()
    data class ErrorCallApi(val error: Throwable?) : ListagemMoedaState()
}
package br.com.silas.conversordemoedas.viewmodel.states.listaMoeda

import br.com.silas.conversordemoedas.model.Moeda

open class ListagemMoedaState {
    data class SucessCallApi(val value: List<Moeda>?): ListagemMoedaState()
    data class ErrorCallApi(val error: Throwable?) : ListagemMoedaState()
}
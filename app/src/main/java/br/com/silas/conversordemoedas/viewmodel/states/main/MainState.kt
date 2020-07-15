package br.com.silas.conversordemoedas.viewmodel.states.main

open class MainState {
    object SucessoSalvarOpcao: MainState()
    data class ErroSalvarOpcao(val value: Boolean): MainState()
    data class SetaUltimoValorSelecionadoNoSwith(val valor: Boolean) : MainState()
}
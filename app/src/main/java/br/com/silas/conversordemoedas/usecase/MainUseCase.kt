package br.com.silas.conversordemoedas.usecase

import br.com.silas.conversordemoedas.repository.MainRepository

class MainUseCase(private val repository: MainRepository) {

    suspend fun salvaSeEhParaUsarDadosMoveis(value: Boolean) =
        repository.salvaSeEhParaUsarDadosMoveis(value)

    suspend fun buscaUltimoValorSelecionadoNoSwitch() =
        repository.buscaUltimoValorSelecionadoNoSwitch()
}
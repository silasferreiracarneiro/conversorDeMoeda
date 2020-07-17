package br.com.silas.conversordemoedas.repository

import br.com.silas.conversordemoedas.data.prefs.SharedPreferencesManager

class MainRepository(private val prefs: SharedPreferencesManager) {

    suspend fun salvaSeEhParaUsarDadosMoveis(value: Boolean) =
        prefs.salvaSeEhParaUsarDadosMoveis(value)

    suspend fun buscaUltimoValorSelecionadoNoSwitch() =
        prefs.getSeEhParaUsarDadosMoveis()
}
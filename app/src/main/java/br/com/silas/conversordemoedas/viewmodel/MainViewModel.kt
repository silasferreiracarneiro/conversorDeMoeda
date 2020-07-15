package br.com.silas.conversordemoedas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.silas.conversordemoedas.provider.providerMainUseCase
import br.com.silas.conversordemoedas.usecase.MainUseCase
import br.com.silas.conversordemoedas.viewmodel.states.main.MainState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val useCase: MainUseCase = providerMainUseCase(application.applicationContext)
    private var state = MutableLiveData<MainState>()

    var viewSate = state

    fun salvaSeEhParaUsarDadosMoveis(value: Boolean) {
        GlobalScope.launch {
            val sucesso = useCase.salvaSeEhParaUsarDadosMoveis(value)
            afterSave(
                sucesso
            )
        }
    }

    private fun afterSave(sucesso: Boolean) {
        when (sucesso) {
            true -> state.postValue(MainState.SucessoSalvarOpcao)
            false -> state.postValue(MainState.ErroSalvarOpcao(!sucesso))
        }
    }

    fun buscaUltimoValorSelecionadoNoSwitch() {
        GlobalScope.launch {
            state.postValue(MainState.SetaUltimoValorSelecionadoNoSwith(useCase.buscaUltimoValorSelecionadoNoSwitch()))
        }
    }
}
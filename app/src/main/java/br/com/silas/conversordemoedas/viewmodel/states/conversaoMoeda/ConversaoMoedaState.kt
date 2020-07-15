package br.com.silas.conversordemoedas.viewmodel.states.conversaoMoeda

open class ConversaoMoedaState {
    object ErroValidacaoValor : ConversaoMoedaState()
    object SucessoValidacaoValor : ConversaoMoedaState()
    object ErroNaChamadaDaApi: ConversaoMoedaState()
    object SucessoNaValidacaoDasMoedas: ConversaoMoedaState()
    data class SucessoNaConversaoDaMoeda(val result: Double?): ConversaoMoedaState()
}
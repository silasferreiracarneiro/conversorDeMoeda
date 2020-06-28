package br.com.silas.conversordemoedas.provider

import br.com.silas.conversordemoedas.usecase.ConversaoMoedaUseCase

@JvmOverloads
fun providerConversaoMoedaUseCase(): ConversaoMoedaUseCase {
    return ConversaoMoedaUseCase(providerTaxaCambioRepository())
}
package br.com.silas.conversordemoedas.provider

import br.com.silas.conversordemoedas.repository.ConversaoMoedaRepository
import br.com.silas.conversordemoedas.usecase.ConversaoMoedaUseCase

fun providerConversaoMoedaRepository(): ConversaoMoedaRepository {
    return ConversaoMoedaRepository(providerApi())
}

@JvmOverloads
fun providerConversaoMoedaUseCase(): ConversaoMoedaUseCase {
    return ConversaoMoedaUseCase(providerConversaoMoedaRepository())
}
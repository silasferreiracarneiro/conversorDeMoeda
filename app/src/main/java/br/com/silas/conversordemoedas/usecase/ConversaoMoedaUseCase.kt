package br.com.silas.conversordemoedas.usecase

import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.repository.ConversaoMoedaRepository
import java.math.BigDecimal

class ConversaoMoedaUseCase(private val repository: ConversaoMoedaRepository) {
    fun validaMoeda(moeda: Moeda?): Boolean = moeda == null

    suspend fun converteMoeda(de: String, para: String, valor: BigDecimal): Double? = null

    fun validaValor(valor: BigDecimal): Boolean = valor == BigDecimal.ZERO
}
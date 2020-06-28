package br.com.silas.conversordemoedas.usecase

import br.com.silas.conversordemoedas.data.network.model.Moeda
import br.com.silas.conversordemoedas.repository.TaxaCambioRepository
import java.math.BigDecimal

class ConversaoMoedaUseCase(private val repository: TaxaCambioRepository) {
    fun validaMoeda(moeda: Moeda?): Boolean = moeda == null

    fun validaValor(valor: BigDecimal): Boolean = valor == BigDecimal.ZERO

    suspend fun getTaxaCambioOnline() = repository.obterTaxaDeCambioOnline()

    suspend fun getTaxaCambioOffline() = repository.obterTaxaDeCambioOffline()

    fun getConversaoCambio(): Double?  = 0.0
}
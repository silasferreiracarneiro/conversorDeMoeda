package br.com.silas.conversordemoedas.usecase

import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.model.TaxaCambio
import br.com.silas.conversordemoedas.repository.TaxaCambioRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigDecimal

class ConversaoMoedaUseCase(private val repository: TaxaCambioRepository) {

    fun validaMoeda(moeda: Moeda?): Boolean = moeda == null

    fun validaValor(valor: BigDecimal): Boolean = valor == BigDecimal.ZERO

    suspend fun getTaxaCambioOnline() = repository.obterTaxaDeCambioOnline()

    suspend fun getTaxaCambioOffline() = repository.obterTaxaDeCambioOffline()

    suspend fun insertTaxaCambio(taxa: TaxaCambio) = repository.insereTaxaCambio(taxa)

    fun conversaoMoedaOffline(
        siglaDe: String,
        siglaPara: String,
        valor: BigDecimal
    ): Double {
        GlobalScope.launch {
            val list = getTaxaCambioOffline()
            efetuarCalculoDeCambio(
                list.findLast { it.indiceTaxa == siglaDe },
                list.findLast { it.indiceTaxa == siglaPara }
            )
        }
        return 0.0
    }

    fun conversaoMoedaOnline(
        lista: List<TaxaCambio>,
        siglaDe: String,
        siglaPara: String,
        valor: BigDecimal
    ): Double {
        efetuarCalculoDeCambio(
            lista.findLast { it.indiceTaxa == siglaDe },
            lista.findLast { it.indiceTaxa == siglaPara }
        )
        return 0.0
    }

    private fun efetuarCalculoDeCambio(siglaDe: TaxaCambio?, siglaPara: TaxaCambio?): Double =
        if (siglaDe == null || siglaPara == null) 0.0
        else siglaDe.valorTaxa * siglaPara.valorTaxa / 100

    fun isOnline(): Boolean = repository.isOnline()
}
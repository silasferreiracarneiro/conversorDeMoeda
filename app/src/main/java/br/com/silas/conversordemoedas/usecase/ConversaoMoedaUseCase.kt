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
        var lista = listOf<TaxaCambio>()
        GlobalScope.launch { lista = getTaxaCambioOffline() }
        return validaLista(lista, siglaDe, siglaPara, valor)
    }

    fun conversaoMoedaOnline(
        lista: List<TaxaCambio>,
        siglaDe: String,
        siglaPara: String,
        valor: BigDecimal
    ): Double {
        return validaLista(
            lista,
            siglaDe,
            siglaPara,
            valor
        )
    }

    private fun validaLista(list: List<TaxaCambio>,
                              siglaDe: String,
                              siglaPara: String,
                              valor: BigDecimal): Double {
        if (list.isNotEmpty()) {
            return efetuarCalculoDeCambio(
                list.findLast { it.indiceTaxa == siglaDe },
                list.findLast { it.indiceTaxa == siglaPara }
            )
        }
        return 0.0
    }

    private fun efetuarCalculoDeCambio(siglaDe: TaxaCambio?, siglaPara: TaxaCambio?): Double =
        if (siglaDe == null || siglaPara == null) 0.0
        else siglaDe.valorTaxa * siglaPara.valorTaxa / 100

    fun isOnline(): Boolean = repository.isOnline()
}
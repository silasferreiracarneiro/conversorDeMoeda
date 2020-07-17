package br.com.silas.conversordemoedas.usecase

import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.model.TaxaCambio
import br.com.silas.conversordemoedas.repository.TaxaCambioRepository
import br.com.silas.conversordemoedas.utils.formatSigla
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
        siglaPara: String,
        valor: BigDecimal,
        listaDeTaxa: List<TaxaCambio>
    ): Double {
        return validaLista(listaDeTaxa, siglaPara, valor)
    }

    fun conversaoMoedaOnline(
        lista: List<TaxaCambio>,
        siglaPara: String,
        valor: BigDecimal
    ): Double {
        return validaLista(
            lista,
            siglaPara,
            valor
        )
    }

    private fun validaLista(list: List<TaxaCambio>,
                              siglaDe: String,
                              valor: BigDecimal): Double {
        if (list.isNotEmpty()) {
            return efetuarCalculoDeCambio(
                list.findLast { it.indiceTaxa == siglaDe.formatSigla() },
                valor
            )
        }
        return 0.0
    }

    private fun efetuarCalculoDeCambio(siglaPara: TaxaCambio?, valor: BigDecimal): Double =
        if (siglaPara == null) 0.0
        else siglaPara.valorTaxa * valor.toDouble()

    suspend fun isOnline(): Boolean = repository.isOnline()
}
package br.com.silas.conversordemoedas.data.network.model

import br.com.silas.conversordemoedas.model.TaxaCambio
import com.google.gson.annotations.SerializedName

class TaxaCambioResponse (
    @SerializedName("success")
    var success : Boolean,
    @SerializedName("terms")
    var terms : String?,
    @SerializedName("privacy")
    var privacy : String?,
    @SerializedName("source")
    var source: String?,
    @SerializedName("quotes")
    var quotes : Map<String, Double>
) {
    fun converteMapParaListaDeTaxaDeCambio(): List<TaxaCambio> {
        if (quotes.isNotEmpty()) {
            return quotes.map {
                TaxaCambio(
                    it.key,
                    it.value
                )
            }
        }
        return emptyList()
    }
}
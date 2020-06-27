package br.com.silas.conversordemoedas.api.model

import com.google.gson.annotations.SerializedName

class TaxaCambioResponse (
    @SerializedName("success")
    var success : Boolean,
    @SerializedName("terms")
    var terms : String?,
    @SerializedName("privacy")
    var privacy : String?,
    @SerializedName("currencies")
    var currencies : Map<String, Double>
) {

}

fun TaxaCambioResponse.converteMapParaListaDeTaxaDeCambio() {

}
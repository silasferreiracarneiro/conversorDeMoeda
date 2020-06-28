package br.com.silas.conversordemoedas.data.network.model

import com.google.gson.annotations.SerializedName

class MoedaResponse (
    @SerializedName("success")
    var success : Boolean,
    @SerializedName("terms")
    var terms : String?,
    @SerializedName("privacy")
    var privacy : String?,
    @SerializedName("currencies")
    var currencies : Map<String,String>
) {

}

fun MoedaResponse.converteMapParaListaDeMoeda(): List<Moeda> {
    return currencies.toList().map {
        Moeda(
            it.first,
            it.second
        )
    }
}

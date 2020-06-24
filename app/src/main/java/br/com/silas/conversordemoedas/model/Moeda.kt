package br.com.silas.conversordemoedas.model

import com.google.gson.annotations.SerializedName

class Moeda (
    @SerializedName("success")
    var success : Boolean,
    @SerializedName("terms")
    var terms : String?,
    @SerializedName("privacy")
    var privacy : String?,
    @SerializedName("currencies")
    var currencies : Map<String,String>
)
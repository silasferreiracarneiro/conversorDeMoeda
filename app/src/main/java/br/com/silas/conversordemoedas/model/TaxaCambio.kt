package br.com.silas.conversordemoedas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaxaCambio (
    @PrimaryKey var indiceTaxa: String,
    @ColumnInfo(name="valorTaxa") var valorTaxa: Double
) {
    var idTaxaSelecionado = ""
}
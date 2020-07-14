package br.com.silas.conversordemoedas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaxaCambio (
    @PrimaryKey var indiceTaxa: String,
    var valorTaxa: Double
)
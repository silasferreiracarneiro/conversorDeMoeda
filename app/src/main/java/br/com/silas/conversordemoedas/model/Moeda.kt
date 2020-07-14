package br.com.silas.conversordemoedas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Moeda(
    @PrimaryKey var sigla: String,
    var nome: String
)
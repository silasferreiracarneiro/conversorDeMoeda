package br.com.silas.conversordemoedas.data.db

import androidx.room.*
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.model.TaxaCambio

@Dao
interface DatabaseDao {
    @Query("select * from taxacambio")
    suspend fun getTaxaCambio(): List<TaxaCambio>

    @Query("select * from moeda")
    suspend fun getMoedas(): List<Moeda>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoeda(vararg breeds: Moeda)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaxaCambio(vararg breeds: TaxaCambio)

    @Delete
    suspend fun delete(breed: TaxaCambio)
}
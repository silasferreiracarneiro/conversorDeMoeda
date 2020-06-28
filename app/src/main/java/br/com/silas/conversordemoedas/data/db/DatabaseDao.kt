package br.com.silas.conversordemoedas.data.db

import androidx.room.*
import br.com.silas.conversordemoedas.data.network.model.TaxaCambio
import kotlinx.coroutines.Deferred

@Dao
interface DatabaseDao {
    @Query("select * from taxacambio")
    fun getTaxaCambio(): List<TaxaCambio>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg breeds: TaxaCambio)

    @Delete
    fun delete(breed: TaxaCambio)
}
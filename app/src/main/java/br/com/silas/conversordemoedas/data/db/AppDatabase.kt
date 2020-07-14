package br.com.silas.conversordemoedas.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.model.TaxaCambio

@Database(entities = [
    TaxaCambio::class,
    Moeda::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "ConversorMoeda"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }

    abstract fun apiDaoLocal(): DatabaseDao
}
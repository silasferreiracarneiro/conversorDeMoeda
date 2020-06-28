package br.com.silas.conversordemoedas.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.silas.conversordemoedas.data.network.model.TaxaCambio

@Database(entities = [TaxaCambio::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val DATABASE_NAME = "ConversorMoedaBTG"

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
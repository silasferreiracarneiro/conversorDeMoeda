package br.com.silas.conversordemoedas.di.module

import android.content.Context
import br.com.silas.conversordemoedas.data.db.AppDatabase
import br.com.silas.conversordemoedas.data.db.DatabaseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(
    val context: Context,
    private val database: DatabaseDao = AppDatabase.invoke(context).apiDaoLocal()
) {

    @Singleton
    @Provides
    fun providesRoomDatabase(): DatabaseDao {
        return database
    }
}
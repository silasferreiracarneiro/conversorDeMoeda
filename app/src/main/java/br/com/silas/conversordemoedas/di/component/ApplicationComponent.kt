package br.com.silas.conversordemoedas.di.component

import android.app.Application
import android.content.Context
import br.com.silas.conversordemoedas.App
import br.com.silas.conversordemoedas.data.db.AppDatabase
import br.com.silas.conversordemoedas.di.ApplicationContext
import br.com.silas.conversordemoedas.di.module.ApplicationModule
import br.com.silas.conversordemoedas.di.module.RoomModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    RoomModule::class
])
interface ApplicationComponent {

    fun inject(app: App)

    @ApplicationContext
    fun context(): Context

    fun application(): Application
}
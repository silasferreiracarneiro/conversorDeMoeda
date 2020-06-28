package br.com.silas.conversordemoedas

import android.app.Application
import android.content.Context
import br.com.silas.conversordemoedas.di.component.ApplicationComponent
import br.com.silas.conversordemoedas.di.component.DaggerApplicationComponent
import br.com.silas.conversordemoedas.di.module.ApplicationModule
import br.com.silas.conversordemoedas.di.module.RoomModule

class App: Application() {

    private lateinit var component: ApplicationComponent
    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .roomModule(RoomModule(this))
            .build()

        component.inject(this)

        context = this
    }

    fun getComponent(): ApplicationComponent {
        return component
    }

    fun getContext(): Context {
        return context
    }
}
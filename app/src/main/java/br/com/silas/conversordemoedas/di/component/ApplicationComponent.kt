package br.com.silas.conversordemoedas.di.component

import android.app.Application
import android.content.Context
import br.com.silas.conversordemoedas.App
import br.com.silas.conversordemoedas.di.ApplicationContext
import br.com.silas.conversordemoedas.di.module.*
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    ApplicationModule::class,
    RoomModule::class,
    RepositoryModule::class,
    UseCaseModule::class,
    ViewModelModule::class,
    ViewModelFactoryModule::class
])
interface ApplicationComponent {

    fun inject(app: App)

    @ApplicationContext
    fun context(): Context

    fun application(): Application
}
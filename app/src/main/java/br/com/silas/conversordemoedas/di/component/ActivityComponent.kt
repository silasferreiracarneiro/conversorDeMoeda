package br.com.silas.conversordemoedas.di.component

import br.com.silas.conversordemoedas.di.PerActivity
import dagger.Component

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [])
interface ActivityComponent {
}
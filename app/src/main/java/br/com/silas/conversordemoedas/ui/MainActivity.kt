package br.com.silas.conversordemoedas.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.silas.conversordemoedas.App
import br.com.silas.conversordemoedas.R
import br.com.silas.conversordemoedas.di.component.ActivityComponent
import br.com.silas.conversordemoedas.di.component.DaggerActivityComponent
import br.com.silas.conversordemoedas.di.module.RepositoryModule
import br.com.silas.conversordemoedas.di.module.RoomModule
import br.com.silas.conversordemoedas.di.module.UseCaseModule

class MainActivity : AppCompatActivity() {

    private lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findNavController(R.id.nav_host_fragment)

//        mActivityComponent = DaggerActivityComponent.builder()
//            //.repositoryModule(RepositoryModule())
//            //.roomModule(RoomModule(this))
//            .useCaseModule(UseCaseModule())
//            .applicationComponent((application as App).getComponent())
//            .build()
    }

    fun getActivityComponent(): ActivityComponent {
        return mActivityComponent
    }
}
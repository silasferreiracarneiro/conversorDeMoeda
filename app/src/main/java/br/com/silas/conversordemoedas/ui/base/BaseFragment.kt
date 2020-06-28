package br.com.silas.conversordemoedas.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.silas.conversordemoedas.di.component.ActivityComponent
import br.com.silas.conversordemoedas.ui.MainActivity

abstract class BaseFragment : Fragment() {

    private var mActivity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUp(view)
    }

    fun getActivityComponent(): ActivityComponent? {
        return if (mActivity != null) {
            mActivity?.getActivityComponent()
        } else null
    }
}
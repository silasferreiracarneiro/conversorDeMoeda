package br.com.silas.conversordemoedas.ui.listagemMoeda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.silas.conversordemoedas.R
import br.com.silas.conversordemoedas.viewmodel.ListagemMoedaViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ListagemMoedaFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = ListagemMoedaFragment()
    }

    private lateinit var listagemMoedaViewModel: ListagemMoedaViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_coin_list, container, false)

        bindObservable()

        return root
    }

    private fun bindObservable() {
        listagemMoedaViewModel = ViewModelProviders.of(this).get(ListagemMoedaViewModel::class.java)
    }
}
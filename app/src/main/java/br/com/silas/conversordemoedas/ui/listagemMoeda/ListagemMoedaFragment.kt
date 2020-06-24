package br.com.silas.conversordemoedas.ui.listagemMoeda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.silas.conversordemoedas.R
import br.com.silas.conversordemoedas.adapter.ListaMoedaAdapter
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.viewmodel.ListagemMoedaViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ListagemMoedaFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = ListagemMoedaFragment()
    }

    private lateinit var listagemMoedaViewModel: ListagemMoedaViewModel

    private lateinit var recycler: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_coin_list, container, false)

        bindObservable()
        bindProperties(root)
        listagemMoedaViewModel.getMoedas()

        return root
    }

    private fun bindProperties(root: View) {
        this.recycler = root.findViewById(R.id.recycler_moedas)
    }

    private fun bindObservable() {
        listagemMoedaViewModel = ViewModelProviders.of(this).get(ListagemMoedaViewModel::class.java)
    }

     private fun setListMovie(moedas: ArrayList<Moeda>) {
         recycler.setHasFixedSize(true)
         recycler.layoutManager = LinearLayoutManager(activity)
         recycler.isNestedScrollingEnabled = false
         recycler.adapter = ListaMoedaAdapter(moedas)
         ((recycler.adapter as ListaMoedaAdapter).notifyDataSetChanged())
    }
}
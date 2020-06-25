package br.com.silas.conversordemoedas.ui.listagemMoeda

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.silas.conversordemoedas.R
import br.com.silas.conversordemoedas.adapter.ListaMoedaAdapter
import br.com.silas.conversordemoedas.api.model.MoedaResponse
import br.com.silas.conversordemoedas.api.model.converteMapParaListaDeMoeda
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.utils.Constants.QUEM_CONVERTE
import br.com.silas.conversordemoedas.utils.RecyclerViewItemClickListener
import br.com.silas.conversordemoedas.utils.RecyclerViewItemClickListener.OnItemClickListener
import br.com.silas.conversordemoedas.viewmodel.ListagemMoedaViewModel
import br.com.silas.conversordemoedas.viewmodel.states.listaMoeda.ListagemMoedaState
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ListagemMoedaFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(converter: Int) = ListagemMoedaFragment().apply {
            val bundle = Bundle()
            bundle.putInt(QUEM_CONVERTE, converter)
            this.arguments = bundle
        }
    }

    private lateinit var listagemMoedaViewModel: ListagemMoedaViewModel

    private lateinit var recycler: RecyclerView

    private var quemConverte: Int = 0
    private var moedas = listOf<Moeda>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_coin_list, container, false)

        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme)

        bindProperties(root)
        bindViewModel()
        bindObservable()
        bindEventsProperties()
        bindBundle()
        listagemMoedaViewModel.getMoedas()

        return root
    }

    private fun bindBundle() {
        this.arguments?.getInt(QUEM_CONVERTE)?.let {
             quemConverte = it
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener { dialog: DialogInterface ->
            val dialogc = dialog as BottomSheetDialog
            val bottomSheet = dialogc.findViewById<FrameLayout>(R.id.design_bottom_sheet)
            val bottomSheetBehavior= BottomSheetBehavior.from(bottomSheet as View)
            bottomSheetBehavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
        return bottomSheetDialog
    }
    private fun bindEventsProperties() {
        recycler.addOnItemTouchListener(RecyclerViewItemClickListener(context,
            recycler,
            object : OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    val moeda = moedas[position]
                    listagemMoedaViewModel.setMoedaSelecionada(moeda, quemConverte)
                    dismiss()
                }

                override fun onItemLongClick(view: View?, position: Int) {
                    //Não é necessario
                }
            }))
    }

    private fun bindObservable() {
        this.listagemMoedaViewModel.viewEvent.observe(this, Observer {

        })

        this.listagemMoedaViewModel.viewState.observe(this, Observer {
            when(it) {
                is ListagemMoedaState.SucessCallApi -> sucessoNaChamada(it.value)
                is ListagemMoedaState.ErrorCallApi -> trataErroNaChamada(it.error)
            }
        })
    }

    private fun trataErroNaChamada(error: Throwable?) {

    }

    private fun sucessoNaChamada(moedas: MoedaResponse?) {
        moedas?.converteMapParaListaDeMoeda()?.let { it ->
            this.moedas = it
            setListMoedas()
        }
    }

    private fun bindProperties(root: View) {
        this.recycler = root.findViewById(R.id.recycler_moedas)
    }

    private fun bindViewModel() {
        listagemMoedaViewModel = ViewModelProviders.of(this).get(ListagemMoedaViewModel::class.java)
    }

     private fun setListMoedas() {
         recycler.setHasFixedSize(true)
         recycler.layoutManager = LinearLayoutManager(activity)
         recycler.isNestedScrollingEnabled = false
         recycler.adapter = ListaMoedaAdapter(moedas)
         ((recycler.adapter as ListaMoedaAdapter).notifyDataSetChanged())
    }
}

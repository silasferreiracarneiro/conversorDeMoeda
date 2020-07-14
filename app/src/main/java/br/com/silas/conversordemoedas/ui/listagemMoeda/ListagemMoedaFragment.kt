package br.com.silas.conversordemoedas.ui.listagemMoeda

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.silas.conversordemoedas.R
import br.com.silas.conversordemoedas.adapter.ListaMoedaAdapter
import br.com.silas.conversordemoedas.data.network.model.MoedaResponse
import br.com.silas.conversordemoedas.data.network.model.converteMapParaListaDeMoeda
import br.com.silas.conversordemoedas.model.Moeda
import br.com.silas.conversordemoedas.utils.Constants.QUEM_CONVERTE
import br.com.silas.conversordemoedas.viewmodel.ListagemMoedaViewModel
import br.com.silas.conversordemoedas.viewmodel.states.listaMoeda.ListagemMoedaState
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

class ListagemMoedaFragment : BottomSheetDialogFragment(), MoedaSelecionada {

    companion object {
        fun newInstance(converter: Int) = ListagemMoedaFragment().apply {
            val bundle = Bundle()
            bundle.putInt(QUEM_CONVERTE, converter)
            this.arguments = bundle
        }
    }

    private val listagemMoedaViewModel by activityViewModels<ListagemMoedaViewModel>()

    private lateinit var progressBar: ProgressBar
    private lateinit var containerRecycler: NestedScrollView
    private lateinit var recycler: RecyclerView
    private lateinit var coordinatorLayout: ConstraintLayout

    private var quemConverte: Int = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_coin_list, container, false)

        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogTheme)

        bindProperties(root)
        bindObservable()
        bindBundle()
        listagemMoedaViewModel.getMoedas()

        return root
    }

    private fun bindBundle() {
        this.arguments?.getInt(QUEM_CONVERTE)?.let {
             quemConverte = it
        }
    }

    private fun bindProperties(root: View) {
        this.recycler = root.findViewById(R.id.recycler_moedas)
        this.progressBar = root.findViewById(R.id.progressBar)
        this.containerRecycler = root.findViewById(R.id.container_recycler)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet as FrameLayout)
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)?.windowManager?.defaultDisplay
            ?.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    private fun bindObservable() {
        this.listagemMoedaViewModel.viewState.observe(this, Observer {
            when(it) {
                is ListagemMoedaState.SucessCallApi -> sucessoNaChamada(it.value)
                is ListagemMoedaState.ErrorCallApi -> trataErroNaChamada(it.error)
            }
        })
    }

    private fun trataErroNaChamada(error: Throwable?) {
        goneProgress()
        Snackbar.make(coordinatorLayout, error?.localizedMessage ?: getString(R.string.error_dafault), Snackbar.LENGTH_LONG).show()
        this.dismiss()
    }

    private fun sucessoNaChamada(moedas: MoedaResponse?) {
        moedas?.converteMapParaListaDeMoeda()?.let { it ->
            goneProgress()
            mostraRecylcer()
            setListMoedas(it)
        }
    }

    private fun goneProgress() {
        this.progressBar.visibility = View.GONE
    }

    private fun mostraRecylcer() {
        this.containerRecycler.visibility = View.VISIBLE
    }

     private fun setListMoedas(moedas: List<Moeda>) {
         recycler.setHasFixedSize(true)
         recycler.layoutManager = LinearLayoutManager(activity)
         recycler.isNestedScrollingEnabled = false
         recycler.adapter = ListaMoedaAdapter(moedas, this)
         ((recycler.adapter as ListaMoedaAdapter).notifyDataSetChanged())
    }

    override fun clickItem(moeda: Moeda) {
        listagemMoedaViewModel.setMoedaSelecionada(moeda, quemConverte)
        dismiss()
    }
}

interface MoedaSelecionada {
    fun clickItem(moeda: Moeda)
}
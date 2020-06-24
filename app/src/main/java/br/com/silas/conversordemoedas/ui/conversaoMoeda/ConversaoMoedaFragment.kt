package br.com.silas.conversordemoedas.ui.conversaoMoeda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.silas.conversordemoedas.R
import br.com.silas.conversordemoedas.ui.listagemMoeda.ListagemMoedaFragment
import br.com.silas.conversordemoedas.viewmodel.ConversaoMoedaViewModel

class ConversaoMoedaFragment : Fragment() {

    private lateinit var conversaoMoedaViewModel: ConversaoMoedaViewModel

    private lateinit var btnDe: Button
    private lateinit var btnPara: Button
    private lateinit var btnConverter: Button
    private lateinit var btnInverte: ImageButton
    private lateinit var titleDe: TextView
    private lateinit var titlePara: TextView
    private lateinit var campoValor: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_convesao_moeda, container, false)

        bindProperties(root)
        bindEventsProperties()
        bindViewModel()
        bindObservable()

        return root
    }

    private fun bindProperties(view: View) {
        btnDe = view.findViewById(R.id.btn_converter_de)
        btnPara = view.findViewById(R.id.btn_converter_para)
        btnConverter = view.findViewById(R.id.btn_converter)
        btnInverte = view.findViewById(R.id.btn_inverter)
        titleDe = view.findViewById(R.id.text_title_de)
        titlePara = view.findViewById(R.id.text_title_para)
        campoValor = view.findViewById(R.id.edit_valor)
    }

    private fun bindEventsProperties() {
        btnDe.setOnClickListener {
            activity?.let {
                ListagemMoedaFragment.newInstance().show(it.supportFragmentManager, this.tag)
            }
        }

        btnPara.setOnClickListener {
            activity?.let {
                ListagemMoedaFragment.newInstance().show(it.supportFragmentManager, this.tag)
            }
        }
    }

    private fun bindViewModel() {
        conversaoMoedaViewModel = ViewModelProviders.of(this).get(ConversaoMoedaViewModel::class.java)
    }

    private fun bindObservable() {

    }
}
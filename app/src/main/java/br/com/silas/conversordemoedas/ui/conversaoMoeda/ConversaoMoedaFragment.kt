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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import br.com.silas.conversordemoedas.R
import br.com.silas.conversordemoedas.data.network.model.Moeda
import br.com.silas.conversordemoedas.ui.listagemMoeda.ListagemMoedaFragment
import br.com.silas.conversordemoedas.utils.Constants.CONVERTER_DE
import br.com.silas.conversordemoedas.utils.Constants.CONVERTER_PARA
import br.com.silas.conversordemoedas.viewmodel.ConversaoMoedaViewModel
import br.com.silas.conversordemoedas.viewmodel.ListagemMoedaViewModel
import br.com.silas.conversordemoedas.viewmodel.states.conversaoMoeda.ConversaoMoedaState
import java.math.BigDecimal

class ConversaoMoedaFragment : Fragment() {

    private val conversaoMoedaViewModel by activityViewModels<ConversaoMoedaViewModel>()
    private val listagemMoedaViewModel by activityViewModels<ListagemMoedaViewModel>()

    private lateinit var btnDe: Button
    private lateinit var btnPara: Button
    private lateinit var btnConverter: Button
    private lateinit var btnInverte: ImageButton
    private lateinit var titleDe: TextView
    private lateinit var titlePara: TextView
    private lateinit var campoValor: EditText
    private lateinit var tvResultado: TextView

    private var moedaDe: Moeda? = null
    private var moedaPara: Moeda? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_convesao_moeda, container, false)

        bindProperties(root)
        bindEventsProperties()
        bindObservable()
        bindEventsProperties()

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
        tvResultado = view.findViewById(R.id.resultado_conversao)
    }

    private fun bindEventsProperties() {
        btnDe.setOnClickListener { iniciaBottomSheetListaDeMoeda(CONVERTER_DE) }
        btnPara.setOnClickListener { iniciaBottomSheetListaDeMoeda(CONVERTER_PARA) }
        btnConverter.setOnClickListener {
            conversaoMoedaViewModel.validaValorSelecionado(getValorEscolhido())
        }
        btnInverte.setOnClickListener {
            listagemMoedaViewModel.inverteValoresDoBotao()
        }
    }

    private fun formataString(value: String?): String {
        return value?.let {
            it
        } ?: getString(R.string.vazio)
    }

    private fun getValorEscolhido(): BigDecimal {
        return campoValor.text?.let {
            BigDecimal(it.toString())
        } ?: BigDecimal.ZERO
    }

    private fun bindObservable() {
        conversaoMoedaViewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ConversaoMoedaState.SucessoNaValidacaoDasMoedas -> habilitaBotaoConverterValor()
                is ConversaoMoedaState.SucessoValidacaoValor -> efetuaAhConversaoDoValor()
                is ConversaoMoedaState.ErroValidacaoValor -> null
                is ConversaoMoedaState.ErroNaChamadaDaApi -> null
                is ConversaoMoedaState.SucessoNaChamadaDaApi -> mostraResultadoDaApiParaOhUsuario(it.result)
            }
        })

        listagemMoedaViewModel.moedaEscolhidaDe.observe(viewLifecycleOwner, Observer {
            configuraMoedaDeSelecionada(it)
        })

        listagemMoedaViewModel.moedaEscolhidaPara.observe(viewLifecycleOwner, Observer {
            configuraMoedaParaSelecionada(it)
        })
    }

    private fun mostraResultadoDaApiParaOhUsuario(valor: Double?) {
        valor?.let {
            tvResultado.text = it.toString()
        }
    }

    private fun efetuaAhConversaoDoValor() {
        conversaoMoedaViewModel.converte(
            isOnline = true,
            sigla = formataString(moedaDe?.sigla),
            nome = formataString(moedaPara?.nome),
            valor = getValorEscolhido()
        )
    }

    private fun habilitaBotaoConverterValor() {
        btnConverter.isEnabled = true
    }

    private fun configuraMoedaDeSelecionada(it: Moeda?) {
        moedaDe = it
        btnDe.text = getSigla(it?.sigla)
        titleDe.text = getSigla(it?.sigla)
        conversaoMoedaViewModel.validacaoDasMoedasSelecionadas(moedaDe, moedaPara)
    }

    private fun getSigla(value: String?) = value?.let { it } ?: getString(R.string.n_a)

    private fun configuraMoedaParaSelecionada(it: Moeda?) {
        moedaPara = it
        btnPara.text = getSigla(it?.sigla)
        titlePara.text = getSigla(it?.sigla)
        conversaoMoedaViewModel.validacaoDasMoedasSelecionadas(moedaDe, moedaPara)
    }

    private fun iniciaBottomSheetListaDeMoeda(converter: Int) {
        activity?.let {
            ListagemMoedaFragment.newInstance(converter).show(it.supportFragmentManager, this.tag)
        }
    }
}
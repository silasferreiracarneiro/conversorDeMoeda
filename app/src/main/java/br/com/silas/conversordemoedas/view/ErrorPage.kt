package br.com.silas.conversordemoedas.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.silas.conversordemoedas.R

class ErrorPage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var errorText: TextView

    init {
        val view = View.inflate(context, R.layout.custom_screen_error, this)

        bindProperties(view)
    }

    private fun bindProperties(view: View) {
        errorText = view.findViewById(R.id.edit_text_error)
    }

    fun setMensagemErro(mensagem: String) {
        errorText.text = mensagem
    }

    fun executarFuncaoQueDeuErrado(prepara: () -> Unit, executa: () -> Unit) {
        prepara()
        executa()
    }
}
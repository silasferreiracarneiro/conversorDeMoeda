package br.com.silas.conversordemoedas.ui

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import br.com.silas.conversordemoedas.R
import br.com.silas.conversordemoedas.viewmodel.MainViewModel
import br.com.silas.conversordemoedas.viewmodel.states.main.MainState

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: MainViewModel

    private lateinit var switch: SwitchCompat
    private lateinit var txtTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindProperties()
        inicializaViewmodel()
        iniciaEventos()
        iniciaObservable()
        buscaUltimoValorSelecionadoNoSwitch()

        findNavController(R.id.nav_host_fragment)
    }

    private fun buscaUltimoValorSelecionadoNoSwitch() {
        viewmodel.buscaUltimoValorSelecionadoNoSwitch()
    }

    private fun bindProperties() {
        switch = findViewById(R.id.switch_online_offline)
        txtTitle = findViewById(R.id.titulo_online_offline)
    }

    private fun inicializaViewmodel() {
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun iniciaEventos() {
        switch.setOnCheckedChangeListener { _, isChecked ->
            viewmodel.salvaSeEhParaUsarDadosMoveis(isChecked)
            setaValorNoSwitch(isChecked)
        }
    }

    private fun iniciaObservable() {
        viewmodel.viewSate.observe(this, Observer {
            when (it){
                is MainState.SucessoSalvarOpcao -> emiteAlertaQueVaiUsarOsDadosMoveis()
                is MainState.ErroSalvarOpcao -> setaValorNoSwitch(it.value)
                is MainState.SetaUltimoValorSelecionadoNoSwith -> setaValorNoSwitch(it.valor)
            }
        })
    }

    private fun emiteAlertaQueVaiUsarOsDadosMoveis() {
        if (switch.isChecked) {
            val dialog = AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(getString(R.string.titulo_atencao))
                .setMessage(getString(R.string.mensagem_uso_internet))
                .setPositiveButton(getString(R.string.confirmar))
                { p0, _ -> p0?.dismiss() }
                .setNegativeButton(getString(R.string.cancelar))
                { p0, _ ->
                    setaValorNoSwitch(false)
                    p0?.dismiss()
                }.show()
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
        }
    }

    private fun setaValorNoSwitch(value: Boolean) {
        switch.isChecked = value
        txtTitle.text = if (value) getString(R.string.on_line) else getString(R.string.off_line)
    }
}
package br.com.silas.conversordemoedas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.silas.conversordemoedas.R
import br.com.silas.conversordemoedas.model.Moeda

class ListaMoedaAdapter(val moedas: ArrayList<Moeda>): RecyclerView.Adapter<ListaMoedaAdapter.ListaMoedaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaMoedaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_lista_moeda, parent, false)
        return ListaMoedaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moedas.size
    }

    override fun onBindViewHolder(holder: ListaMoedaViewHolder, position: Int) {
        val item = moedas[position]
        holder.bind(item)
    }

    class ListaMoedaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var txtCodigo = itemView.findViewById<TextView>(R.id.txt_codigo)
        var txtNome = itemView.findViewById<TextView>(R.id.txt_nome)

        fun bind(moeda: Moeda) {
            txtCodigo.text = "Teste"
            txtNome.text = "Teste"
        }
    }
}
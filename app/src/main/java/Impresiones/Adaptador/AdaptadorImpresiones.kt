package Impresiones.Adaptador

import Impresiones.Clases.Impresion
import ViewHolderImpresiones
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R

class AdaptadorImpresiones(var listaImpresiones:List<Impresion>) : RecyclerView.Adapter<ViewHolderImpresiones>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderImpresiones {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderImpresiones(layoutInflater.inflate(R.layout.impresiones, parent, false))
    }

    override fun getItemCount(): Int = listaImpresiones.size

    override fun onBindViewHolder(holder: ViewHolderImpresiones, position: Int) {
        val item = listaImpresiones[position]
        holder.render(item)
    }

}
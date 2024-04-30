package Filamentos.Adaptador

import Filamentos.Clases.Filamento
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R

class AdaptadorFilamentos(var listaFilamentos:List<Filamento>) : RecyclerView.Adapter<ViewHolderFilamentos>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilamentos {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderFilamentos(layoutInflater.inflate(R.layout.filamentos, parent, false))
    }

    override fun getItemCount(): Int = listaFilamentos.size

    override fun onBindViewHolder(holder: ViewHolderFilamentos, position: Int) {
        val item = listaFilamentos[position]
        holder.render(item)
    }

}
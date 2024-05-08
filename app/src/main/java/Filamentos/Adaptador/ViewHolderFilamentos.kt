package Filamentos.Adaptador

import Filamentos.Clases.Filamento
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a3dlab.R

class ViewHolderFilamentos(view:View):RecyclerView.ViewHolder(view) {
    private val color = view.findViewById<TextView>(R.id.Color)
    private val marca = view.findViewById<TextView>(R.id.Marca)
    private val precio = view.findViewById<TextView>(R.id.precio)
    private val restante = view.findViewById<TextView>(R.id.restante)
    private val foto = view.findViewById<ImageView>(R.id.ImagenF)
    fun render(filamento: Filamento){
        color.text = filamento.color
        marca.text = filamento.marca
        precio.text = "$ ${filamento.precioxkg}"
        restante.text = "${filamento.restante} kg"
        Glide.with(color.context).load(filamento.foto).into(foto)
    }
}
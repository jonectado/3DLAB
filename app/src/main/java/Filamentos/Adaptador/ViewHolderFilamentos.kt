package Filamentos.Adaptador

import Filamentos.Clases.Filamento
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ViewHolderFilamentos(view: View) : RecyclerView.ViewHolder(view) {
    private val color = view.findViewById<TextView>(R.id.Color)
    private val marca = view.findViewById<TextView>(R.id.Marca)
    private val precio = view.findViewById<TextView>(R.id.precio)
    private val restante = view.findViewById<TextView>(R.id.restante)
    private val foto = view.findViewById<ImageView>(R.id.ImagenF)
    private val estado = view.findViewById<Button>(R.id.estadoF)
    private val tipo = view.findViewById<TextView>(R.id.Tipo)
    private var seleccion = 0
    fun render(filamento: Filamento) {
        color.text = filamento.color
        marca.text = filamento.marca
        tipo.text = filamento.tipo_material
        precio.text = "$ ${filamento.costo}"
        restante.text = "${filamento.restante} kg"
        estado.text = filamento.estado
        when (filamento.estado) {
            "Disponible" -> {
                estado.setBackgroundResource(R.drawable.boton_completada)
            }
            "Acabandose" -> {
                estado.setBackgroundResource(R.drawable.boton_en_proceso)
            }
            else -> {
                estado.setBackgroundResource(R.drawable.boton_fallida)
            }
        }
        estado.setOnClickListener {
            val builderSingle = AlertDialog.Builder(estado.context)
            builderSingle.setTitle("Eliga el estado de su impresion")
            builderSingle.setPositiveButton("ok") { dialog, _ -> dialog.dismiss() }
            builderSingle.setSingleChoiceItems(
                arrayOf("Disponible", "Acabandose", "No disponible"),
                seleccion
            ) { dialog, whitch ->
                seleccion = whitch
                estado.text = arrayOf("Disponible", "Acabandose", "No disponible")[seleccion]
                when (seleccion) {
                    0 -> {
                        estado.setBackgroundResource(R.drawable.boton_completada)
                        Firebase.firestore.collection("Filamentos").document(filamento.id)
                            .update("estado", "Disponible")
                    }

                    1 -> {
                        estado.setBackgroundResource(R.drawable.boton_en_proceso)
                        Firebase.firestore.collection("Filamentos").document(filamento.id)
                            .update("estado", "Acabandose")
                    }

                    2 -> {
                        estado.setBackgroundResource(R.drawable.boton_fallida)
                        Firebase.firestore.collection("Filamentos").document(filamento.id)
                            .update("estado", "No disponible")
                    }
                }
            }
            builderSingle.show()
        }
        Glide.with(color.context).load(filamento.foto).into(foto)
    }
}
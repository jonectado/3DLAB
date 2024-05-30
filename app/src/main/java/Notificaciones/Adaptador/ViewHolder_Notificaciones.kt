package Notificaciones.Adaptador

import Notificaciones.Clases.Notificacion
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.firestore

class ViewHolder_Notificaciones(view: View) : RecyclerView.ViewHolder(view) {

    val notificacion_mostrada = view.findViewById<TextView>(R.id.texto_noti_general)
    var img_mostrada = view.findViewById<ImageView>(R.id.img_general)
    val botonBorrado = view.findViewById<Button>(R.id.boton_borrado)


    @SuppressLint("SetTextI18n")
    fun render(notificacionModel: Notificacion) {
        println("\n AQUI INICIA EL RENDER: ${notificacionModel.IdFilamento}")
        if (notificacionModel.IdFilamento.isNotEmpty()) {
            print("FILAMENTO: ")
                println(notificacionModel.IdFilamento)
                Firebase.firestore.collection("Filamentos")
                    .document(notificacionModel.IdFilamento)
                    .get().addOnSuccessListener { fil ->
                        val color = fil.data!!["color"].toString()

                        val marca = fil.data!!["marca"].toString()
                        notificacion_mostrada.text =
                            "Se acaba el filamento\n ${notificacionModel.IdFilamento}. $color $marca"

                    }
                botonBorrado.setOnClickListener {
                    Firebase.firestore.collection("Notificaciones")
                        .document("Filamentos")
                        .set(mapOf(notificacionModel.IdFilamento to ""))
                    img_mostrada.setImageResource(R.drawable.filamento)
                }
        }
        else if (notificacionModel.usos.isNotEmpty() && notificacionModel.usos.toInt() > 7) {
            println("\n AQUI CONTINUA EL RENDER: ${notificacionModel.usos}")
                notificacion_mostrada.text = "Deberias lavar la cama de impresión"
                img_mostrada.setImageResource(R.drawable.impresion)
                botonBorrado.setOnClickListener {
                    Firebase.firestore.collection("Notificaciones")
                        .document("Usos")
                        .set(mapOf("usos" to 0))
            }
        }else if (notificacionModel.usos.isNotEmpty() && notificacionModel.usosBig.toInt() > 15) {
                notificacion_mostrada.text = "Deberias lubricar los ejes"
                img_mostrada.setImageResource(R.drawable.impresion)
                botonBorrado.setOnClickListener {
                    Firebase.firestore.collection("Notificaciones")
                        .document("Usos")
                        .set(mapOf("usosBig" to 0) )
                }
            }

        }



    }


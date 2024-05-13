package Notificaciones.Adaptador

import Notificaciones.Clases.Notificacion
import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a3dlab.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewHolder_Notificaciones (view:View):RecyclerView.ViewHolder (view){

    val notificacion_mostrada = view.findViewById<TextView>(R.id.texto_noti_general)
    var img_mostrada = view.findViewById<ImageView>(R.id.img_general)

    fun render(notificacionModel:Notificacion){
        when (notificacionModel.notificacion_text){

            "cama" -> {
                notificacion_mostrada.text = "Deberias lavar la cama de \n impresión"
                img_mostrada.setImageResource(R.drawable.impresion)
            }
            "ejes" -> {
                notificacion_mostrada.text = "Deberias lubricar los ejes"
                img_mostrada.setImageResource(R.drawable.impresion)
            }
            "filamento" -> {

                Firebase.firestore.collection("Filamentos")
                    .document(notificacionModel.filamento).get().addOnSuccessListener {
                        fil -> notificacion_mostrada.text = "Se acaba el filamento \n ${fil.data!!["color"]} ${fil.data!!["marca"]}"
                    }
                img_mostrada.setImageResource(R.drawable.filamento)
            }
        }
    }
}
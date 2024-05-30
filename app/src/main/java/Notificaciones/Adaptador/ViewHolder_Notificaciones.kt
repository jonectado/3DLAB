package Notificaciones.Adaptador

import Notificaciones.Clases.Notificacion
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ViewHolder_Notificaciones(view: View) : RecyclerView.ViewHolder(view) {

    val notificacion_mostrada = view.findViewById<TextView>(R.id.texto_noti_general)
    var img_mostrada = view.findViewById<ImageView>(R.id.img_general)


    fun render(notificacionModel: Notificacion) {

        if(notificacionModel.PesoFilamento.isNotEmpty()){
            Firebase.firestore.collection("Filamentos")
                .document(notificacionModel.PesoFilamento).get().addOnSuccessListener {
                        fil -> notificacion_mostrada.text = "Se acaba el filamento \n ${fil.data!!["color"]} ${fil.data!!["marca"]}"
                }
            img_mostrada.setImageResource(R.drawable.filamento)
        }

        if(notificacionModel.cama.toInt() > 7){
            notificacionModel.notificacion_text = "Deberias lavar la cama de \n impresión"
            img_mostrada.setImageResource(R.drawable.impresion)
        }

        if(notificacionModel.ejes.toInt() > 15){
            notificacionModel.notificacion_text = "Deberias lubricar los ejes"
            img_mostrada.setImageResource(R.drawable.impresion)
        }
    }
}

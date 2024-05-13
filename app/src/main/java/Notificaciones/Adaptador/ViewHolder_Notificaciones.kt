package Notificaciones.Adaptador

import Notificaciones.Clases.Notificacion
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R

class ViewHolder_Notificaciones (view:View):RecyclerView.ViewHolder (view){

    val notificacion_mostrada = view.findViewById<TextView>(R.id.texto_noti_general)
    val img_mostrada = view.findViewById<ImageView>(R.id.img_general)
    fun render(notificacionModel:Notificacion){
        notificacion_mostrada.text = notificacionModel.notificacion_text
    }
}
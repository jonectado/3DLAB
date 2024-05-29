package Notificaciones.Adaptador

import Impresiones.Clases.Impresion
import android.view.LayoutInflater
import Notificaciones.Clases.Notificacion
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R

class adaptador_notificacion(private val noti_list: List<Notificacion>) :
    RecyclerView.Adapter<ViewHolder_Notificaciones>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder_Notificaciones {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder_Notificaciones(
            layoutInflater.inflate(
                R.layout.notificacion_general,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder_Notificaciones, position: Int) {
        val item = noti_list[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return noti_list.size
    }
}


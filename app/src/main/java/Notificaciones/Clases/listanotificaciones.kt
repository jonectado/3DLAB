package Notificaciones.Clases

import com.example.a3dlab.R

class listanotificaciones {
    companion object{
        val noti_list = listOf<Notificacion>(
            Notificacion("ejes", "0"),
            Notificacion("filamento", "1"),
            Notificacion("cama", "0")
        )
    }
}


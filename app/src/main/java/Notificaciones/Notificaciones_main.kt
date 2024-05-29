package Notificaciones

import Dashboard.dashboard_main
import Impresiones.ImpresionesMain
import Notificaciones.Adaptador.adaptador_notificacion
import Notificaciones.Clases.Notificacion
import Notificaciones.Clases.listanotificaciones
import Notificaciones.Notificaciones_main
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R

class Notificaciones_main : AppCompatActivity() {

    private lateinit var Backbutton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_notificaciones)
        initRecyclerView()

        Backbutton = findViewById(R.id.backButtonN)

        Backbutton.setOnClickListener {
            val intent = Intent(this, dashboard_main::class.java)
            startActivity(intent)
        }
    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.lista)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adaptador_notificacion(listanotificaciones.noti_list)
    }
}




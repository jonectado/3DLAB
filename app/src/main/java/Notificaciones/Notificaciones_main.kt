package Notificaciones

import Dashboard.dashboard_main
import Impresiones.Clases.Impresion
import Notificaciones.Adaptador.adaptador_notificacion
import Notificaciones.Clases.Notificacion
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R
import com.google.firebase.firestore.FirebaseFirestore

class Notificaciones_main : AppCompatActivity() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var notificationsAdapter: ArrayAdapter<String>
    private val noti_list: ArrayList<Notificacion> = ArrayList<Notificacion>()
    private val notificacionId = mutableListOf<String>()

    private lateinit var Backbutton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_notificaciones)
        initRecyclerView()

        Backbutton = findViewById(R.id.backButtonN)

        Backbutton.setOnClickListener {
            val intent = Intent(this, dashboard_main::class.java)
            startActivity(intent)
            finish()
        }

        cargar_notificacion()
    }


    fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.lista)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adaptador_notificacion(noti_list)
    }

    private fun cargar_notificacion() {
        db.collection("Notificaciones")
            .get()
            .addOnSuccessListener { result ->
                noti_list.clear()
                notificationsAdapter.notifyDataSetChanged()
            }
    }

    private fun deleteNotification(position: Int) {
        val notificationId = notificacionId[position]
        db.collection("Notificaciones").document(notificationId)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Notificación eliminada", Toast.LENGTH_SHORT).show()
                notificacionId.removeAt(position)
                notificacionId.removeAt(position)
                notificationsAdapter.notifyDataSetChanged()
                updateCounter()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error eliminando notificación", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateCounter() {
        db.collection("Impresiones")
            .get()
            .addOnSuccessListener { result ->
                val count = result.size()
                db.collection("ConteoImpresiones")
                    .document("conteo")
                    .set(mapOf("count" to count))
            }
    }
}




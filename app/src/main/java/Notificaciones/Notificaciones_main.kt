package Notificaciones

import Dashboard.dashboard_main
import Impresiones.Adaptador.AdaptadorImpresiones
import Impresiones.Clases.Impresion
import Notificaciones.Adaptador.adaptador_notificacion
import Notificaciones.Clases.Notificacion
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class Notificaciones_main : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: adaptador_notificacion
    private val noti_list: ArrayList<Notificacion> = ArrayList<Notificacion>()
    private lateinit var Hecho_button: Button
    private lateinit var Backbutton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_notificaciones)
        recyclerView = findViewById<RecyclerView>(R.id.lista)
        recyclerView.adapter = adaptador_notificacion(noti_list)

        initRecyclerView()
        initNotificacion()

        Backbutton = findViewById(R.id.backButtonN)

        Backbutton.setOnClickListener {
            val intent = Intent(this, dashboard_main::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun initRecyclerView() {
        noti_list.clear()
        db.collection("Notificaciones")
            .get()
            .addOnSuccessListener { p ->
                noti_list.clear()
                for(document in p){
                    var archivo: Notificacion = Notificacion(
                        document.data!!["notificacion_text"].toString(),
                        document.data!!["PesoFilamento"].toString(),
                        document.data!!["cama"].toString(),
                        document.data!!["ejes"].toString()
                    )
                    noti_list.add(archivo)
                }
            }
        if (noti_list.isEmpty()) {
            Toast.makeText(this, "Sin Notificaciones", Toast.LENGTH_SHORT).show()
        } else {
            noti_list.sortBy { "id" }
            noti_list.reverse()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adaptador_notificacion(noti_list)
        }
    }

    fun initNotificacion() {

        db.collection("Notificacion")
            .get()
            .addOnSuccessListener { resp ->
                noti_list.clear()
                for (document in resp) {
                    if (document.data["Fil_restante"].toString().toInt() < 50) {
                        val archivo: String =
                            document.data["Fil_restante"].toString()
                        noti_list.plus(archivo)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}




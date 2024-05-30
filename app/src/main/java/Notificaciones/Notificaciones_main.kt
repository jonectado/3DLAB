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
    private val noti_list: ArrayList<Notificacion> = ArrayList<Notificacion>()
    private lateinit var Backbutton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_notificaciones)
        recyclerView = findViewById<RecyclerView>(R.id.lista)
        recyclerView.adapter = adaptador_notificacion(noti_list)

        initRecyclerView()


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
            .document("Filamentos")
            .get()
            .addOnSuccessListener { p ->
                for (i in p.data!!.toList()){
                    println("FUNCIONA FILAMENTOS: ${i.second.toString()}")
                    if(i.second.toString().isNotEmpty()){
                        noti_list.add(Notificacion(i.second.toString(),"",""))
                    }
                }
                db.collection("Notificaciones")
                    .document("Usos")
                    .get()
                    .addOnSuccessListener { q ->
                        if(q.data!!["usos"].toString().toInt()>7){
                            noti_list.add(Notificacion("",q.data!!["usos"].toString(),""))
                        }
                        if(q.data!!["usosBig"].toString().toInt()>15){
                            noti_list.add(Notificacion("","",q.data!!["usosBig"].toString()))
                        }
                        if (noti_list.isEmpty()) {
                            Toast.makeText(this, "Sin Notificaciones", Toast.LENGTH_SHORT).show()
                        } else {
                            recyclerView.layoutManager = LinearLayoutManager(this)
                            recyclerView.adapter = adaptador_notificacion(noti_list)
                        }
                    }
            }


    }
}




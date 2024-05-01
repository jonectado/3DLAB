package Impresiones

import Impresiones.Adaptador.AdaptadorImpresiones
import Impresiones.Clases.Impresion
import Impresiones.EditarImpresiones.EditarImpresionesMain
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ImpresionesMain : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var addButton: FloatingActionButton
    val db = Firebase.firestore
    private var listaImpresiones : ArrayList<Impresion> = ArrayList<Impresion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_impresiones_main)
        initRecyclerView()
        backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener{
            finish()
        }
        addButton = findViewById<FloatingActionButton>(R.id.anadir)
        addButton.setOnClickListener{
            val intent = Intent(this, EditarImpresionesMain::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        this.initRecyclerView()
    }
    private fun initRecyclerView() {
        listaImpresiones.clear()
        db.collection("Impresiones")
            .get()
            .addOnSuccessListener { result ->
                listaImpresiones.clear()
                for (document in result) {
                    var archivo: Impresion = Impresion(document.data["titulo"].toString(),
                        document.data["descripcion"].toString(),
                        document.data["filamentoUsado"].toString(),
                        document.data["gr_material"].toString(),
                        document.data["valor"].toString(),
                        document.data["foto"].toString(),
                        document.data["id"].toString() )
                    listaImpresiones.add(archivo)
                }
                if(listaImpresiones.isEmpty()){
                    Toast.makeText(this, "Añade tu primera impresión!", Toast.LENGTH_SHORT).show()
                }else{
                    val recyclerview =findViewById<RecyclerView>(R.id.lista)
                    recyclerview.layoutManager = LinearLayoutManager(this)
                    recyclerview.adapter = AdaptadorImpresiones(listaImpresiones)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                val recyclerview =findViewById<RecyclerView>(R.id.lista)
                recyclerview.layoutManager = LinearLayoutManager(this)
                var listaImpresionesNull= listOf(Impresion("No","Funciona","esto", "Aggggg","F","","Aggggg"))
                recyclerview.adapter = AdaptadorImpresiones(listaImpresionesNull)
            }

    }
}
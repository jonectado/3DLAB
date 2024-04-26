package Impresiones

import Impresiones.Adaptador.AdaptadorImpresiones
import Impresiones.Clases.ListaImpresiones
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R

class ImpresionesMain : AppCompatActivity() {
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_impresiones_main)
        initRecyclerView()
        backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener{
            finish()
        }
    }



    private fun initRecyclerView() {
        val recyclerview =findViewById<RecyclerView>(R.id.lista)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = AdaptadorImpresiones(ListaImpresiones.lista)
    }
}
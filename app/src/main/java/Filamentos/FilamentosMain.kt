package Filamentos

import Filamentos.Adaptador.AdaptadorFilamentos
import Filamentos.AnadirFilamentos.AñadirFilamentosMain
import Filamentos.Clases.Filamento
import Impresiones.AnadirImpresiones.AnadirImpresionesMain
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FilamentosMain : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filamentos_main)
        initRecyclerView()
        backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener{
            finish()
        }
        addButton = findViewById(R.id.anadir)
        addButton.setOnClickListener{
            val intent = Intent(this, AñadirFilamentosMain::class.java)
            startActivity(intent)
            initRecyclerView()
        }

    }

    override fun onResume() {
        super.onResume()
        this.initRecyclerView()
    }

    fun initRecyclerView() {
        val recyclerview = findViewById<RecyclerView>(R.id.lista)
        recyclerview.layoutManager = GridLayoutManager(this, 2)
        recyclerview.adapter = AdaptadorFilamentos(listOf<Filamento>())
    }
}
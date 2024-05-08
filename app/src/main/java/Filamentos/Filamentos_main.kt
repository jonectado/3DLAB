package Filamentos

import Filamentos.Adaptador.AdaptadorFilamentos
import Filamentos.Clases.ListaFilamentos
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R

class Filamentos_main : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filamentos_main)
        initRecyclerView()
        backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener{
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        this.initRecyclerView()
    }

    fun initRecyclerView() {
        val recyclerview = findViewById<RecyclerView>(R.id.lista)
        recyclerview.layoutManager = GridLayoutManager(this, 2)
        recyclerview.adapter = AdaptadorFilamentos(ListaFilamentos.lista)
    }
}
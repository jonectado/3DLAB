package Filamentos

import Filamentos.Adaptador.AdaptadorFilamentos
import Filamentos.Clases.ListaFilamentos
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R
import com.example.a3dlab.layout_pagina_principal

class Filamentos_main : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener{
            val intent = Intent(this, layout_pagina_principal::class.java)
            startActivity(intent)
        }
    }

    fun initRecyclerView() {
        val recyclerview =findViewById<RecyclerView>(R.id.lista)
        recyclerview.layoutManager = GridLayoutManager(this,2)
        recyclerview.adapter = AdaptadorFilamentos(ListaFilamentos.lista){
            filamento ->
        }
    }

}
package Filamentos

import Filamentos.Adaptador.AdaptadorFilamentos
import Filamentos.Clases.ListaFilamentos
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R

class Filamentos_main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    fun initRecyclerView() {
        val recyclerview =findViewById<RecyclerView>(R.id.lista)
        recyclerview.layoutManager = GridLayoutManager(this,2)
        recyclerview.adapter = AdaptadorFilamentos(ListaFilamentos.lista)
    }

}
package Dashboard

import Filamentos.FilamentosMain
import Impresiones.ImpresionesMain
import Notificaciones.Notificaciones_main
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.a3dlab.R

class dashboard_main : AppCompatActivity() {

    private lateinit var Miusuario: CardView
    private lateinit var MisImpresiones: CardView
    private lateinit var MisFilamentos: CardView
    private lateinit var Notificaciones: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_pagina_principal)

        Miusuario = findViewById<CardView>(R.id.Miusuario)
        Miusuario.setOnClickListener {
            val intent = Intent(this, ImpresionesMain::class.java)
            startActivity(intent)
        }

        MisImpresiones = findViewById<CardView>(R.id.MisImpresiones)
        MisImpresiones.setOnClickListener {
            val intent = Intent(this, ImpresionesMain::class.java)
            startActivity(intent)
        }

        MisFilamentos = findViewById<CardView>(R.id.MisFilamentos)
        MisFilamentos.setOnClickListener {
            val intent = Intent(this, FilamentosMain::class.java)
            startActivity(intent)
        }
        Notificaciones = findViewById<CardView>(R.id.Notificaciones)
        Notificaciones.setOnClickListener {
            val intent = Intent(this, Notificaciones_main::class.java)
            startActivity(intent)
        }
    }

}


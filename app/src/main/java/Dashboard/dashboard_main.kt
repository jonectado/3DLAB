package Dashboard

import Filamentos.Filamentos_main
import Impresiones.ImpresionesMain
import Notificaciones.Notificaciones_main
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import com.example.a3dlab.R

class dashboard_main : AppCompatActivity() {

    private lateinit var MisImpresiones: LinearLayout
    private lateinit var MisFilamentos: LinearLayout
    private lateinit var Notificaciones: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_pagina_principal)

        MisImpresiones = findViewById<LinearLayout>(R.id.MisImpresiones)
        MisImpresiones.setOnClickListener {
            val intent = Intent(this, ImpresionesMain::class.java)
            startActivity(intent)
        }

        MisFilamentos = findViewById<LinearLayout>(R.id.MisFilamentos)
        MisFilamentos.setOnClickListener {
            val intent = Intent(this, Filamentos_main::class.java)
            startActivity(intent)
        }
        Notificaciones = findViewById<LinearLayout>()
    }
}


package com.example.a3dlab

import Filamentos.Filamentos_main
import Impresiones.ImpresionesMain
import Notificaciones.Notificaciones_main
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class layout_pagina_principal : AppCompatActivity() {

    private lateinit var MisImpresiones: LinearLayout
    private lateinit var MisFilamentos: LinearLayout
    private lateinit var Notificaciones: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_pagina_principal)

        MisImpresiones = findViewById<LinearLayout>(R.id.MisImpresiones)
        MisImpresiones.setOnClickListener{
            val intent = Intent(this, ImpresionesMain::class.java)
            startActivity(intent)
        }
        MisFilamentos = findViewById<LinearLayout>(R.id.MisFilamentos)
        MisFilamentos.setOnClickListener{
            val intent = Intent(this, Filamentos_main::class.java)
            startActivity(intent)
        }
        Notificaciones = findViewById<LinearLayout>(R.id.Notificaciones)
        Notificaciones.setOnClickListener{
            val intent = Intent(this, Notificaciones_main::class.java)
            startActivity(intent)
        }

    }


}
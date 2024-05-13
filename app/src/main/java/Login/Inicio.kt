package Login

import Registro.Registro
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.a3dlab.R

class Inicio : AppCompatActivity() {
    private lateinit var button1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        button1 = findViewById(R.id.button1)
        button1.setOnClickListener{
            ingresar()
        }
    }

    private fun ingresar() {
        val intent = Intent(this, LogInMain::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(
            this,
            "Bienvenido",
            Toast.LENGTH_SHORT
        ).show()
    }
}
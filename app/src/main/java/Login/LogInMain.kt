package Login

import Impresiones.ImpresionesMain
import Registro.Registro
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.a3dlab.R
import com.example.a3dlab.passwordreset
import com.google.firebase.auth.FirebaseAuth

class LogInMain : AppCompatActivity() {

    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var editTextTextPassword: EditText
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var restorePassword:TextView
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_main)
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword = findViewById(R.id.editTextTextPassword)
        firebaseAuth = FirebaseAuth.getInstance()
        button3 = findViewById(R.id.button3)
        restorePassword = findViewById(R.id.restorePassword)

        button3.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString()
            val pass = editTextTextPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent1 = Intent(this, ImpresionesMain::class.java)
                        startActivity(intent1)
                        Toast.makeText(
                            this,
                            "Inicio de sesión exitoso",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(
                            this,
                            it.exception.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    this,
                    "Rellene todos los campos para iniciar sesión",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener{
            val intent2 = Intent(this, Registro::class.java)
            startActivity(intent2)
        }
        restorePassword.setOnClickListener {
            val intent3 = Intent(this, passwordreset::class.java)
            startActivity(intent3)
        }
    }
}
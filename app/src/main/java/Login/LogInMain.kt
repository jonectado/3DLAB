package Login

import Impresiones.ImpresionesMain
import Registro.Registro
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.a3dlab.R
import com.google.firebase.auth.FirebaseAuth

class LogInMain : AppCompatActivity() {

    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var editTextTextPassword: EditText
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_main)

        firebaseAuth = FirebaseAuth.getInstance()

        button2.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString()
            val pass = editTextTextPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent1 = Intent(this, ImpresionesMain::class.java)
                        startActivity(intent)
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

        button3.setOnClickListener{
            val intent2 = Intent(this, Registro::class.java)
            startActivity(intent2)
        }
    }
}
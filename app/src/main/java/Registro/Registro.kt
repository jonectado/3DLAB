package Registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.a3dlab.R
import com.google.firebase.auth.FirebaseAuth

class Registro : AppCompatActivity() {
    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var editTextTextPassword: EditText
    private lateinit var editTextTextPassword2:EditText
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        firebaseAuth = FirebaseAuth.getInstance()
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword = findViewById(R.id.editTextTextPassword)
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button2.setOnClickListener {
            registrar()
        }
        button3.setOnClickListener {
            finish()
        }
    }

    private fun registrar() {
        val email = editTextTextEmailAddress.text.toString()
        val pass = editTextTextPassword.text.toString()
        val confirmPass = editTextTextPassword2.text.toString()
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            if(pass == confirmPass){
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(
                            this,
                            "Usuario añadido correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }else{
                        Toast.makeText(
                            this,
                            it.exception.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }else{
                Toast.makeText(
                    this,
                    "Las contraseñas no coinciden",
                    Toast.LENGTH_SHORT
                ).show()
            }

        } else {
            Toast.makeText(
                this,
                "Rellene todos los campos para iniciar sesión",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
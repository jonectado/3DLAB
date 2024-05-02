package Registro

import Login.LogInMain
import android.content.Intent
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
        button2.setOnClickListener {
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
                            val intent = Intent(this, LogInMain::class.java)
                            startActivity(intent)
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
}
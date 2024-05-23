package Registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.snapshots

class Registro : AppCompatActivity() {
    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var editTextTextPassword: EditText
    private lateinit var editTextTextPassword2: EditText
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        firebaseAuth = FirebaseAuth.getInstance()
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword = findViewById(R.id.editTextTextPassword)
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        setId()

        button2.setOnClickListener {
            registrar()
        }
        button3.setOnClickListener {
            finish()
        }
    }

    fun registrar() {
        val email = editTextTextEmailAddress.text.toString()
        val pass = editTextTextPassword.text.toString()
        val confirmPass = editTextTextPassword2.text.toString()
        Toast.makeText(
            this,
            "Cargando...",
            Toast.LENGTH_SHORT
        ).show()
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            if (pass == confirmPass) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Firebase.firestore.collection("Usuarios")
                            .document(id)
                            .set(
                                mapOf<String, String>(
                                    Pair(
                                        "correo",
                                        firebaseAuth.currentUser!!.email.toString()
                                    )
                                )
                            )
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "Usuario añadido correctamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        finish()
                    } else {
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

    fun setId() {
        var userId = listOf<Int>()
        Firebase.firestore.collection("Usuarios").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    userId = userId.plus(document.id.toInt())
                }
                id = if (userId.isEmpty()) {
                    "1"
                } else {
                    (userId.max() + 1).toString()
                }
            }
    }
}
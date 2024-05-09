package Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.a3dlab.R
import com.google.firebase.auth.FirebaseAuth

class passwordreset : AppCompatActivity() {
    private lateinit var button2: Button
    private lateinit var editTextTextEmailAddress: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passwordreset)

        button2 = findViewById(R.id.button2)
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)

        button2.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString()
            if (email.isNotEmpty()){
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{
                        task ->
                        if(task.isSuccessful){
                            Toast.makeText(
                                this,
                                "Se ha enviado correo de recuperación",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        }else{
                            Toast.makeText(
                                this,
                                "No se ha podido enviar al correo",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }else{
                Toast.makeText(
                    this,
                    "Por favor ingrese su correo",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
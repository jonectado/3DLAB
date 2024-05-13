package usuario

import Login.LogInMain
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a3dlab.R
import com.google.firebase.auth.FirebaseAuth
import Login.passwordreset


class usuario : AppCompatActivity() {
    private lateinit var email:TextView
    private var userEmail:String = ""
    private lateinit var backbutton: Button
    private lateinit var button2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)
        backbutton = findViewById(R.id.backButton)
        button2 = findViewById(R.id.button2)

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            userEmail = user.email.toString()
        } else {
            // No user is signed in
        }
        email = findViewById(R.id.textView4)
        email.text = userEmail

        backbutton.setOnClickListener {
            finish()
        }
        
        button2.setOnClickListener{
            val intent = Intent(this, passwordreset::class.java)
            startActivity(intent)
        }
    }
}
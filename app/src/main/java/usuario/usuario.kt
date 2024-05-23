package usuario

import Login.passwordreset
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


class usuario : AppCompatActivity() {
    private lateinit var email:TextView
    private var userEmail:String = ""
    private lateinit var backbutton: ImageButton
    private lateinit var button2: Button
    private lateinit var lista: ListView
    private var lista_usuarios:ArrayList<String> = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)
        backbutton = findViewById(R.id.backButton)
        button2 = findViewById(R.id.button2)
        lista = findViewById(R.id.listView)
        updateUsers()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            userEmail = user.email.toString()
        } else {
            // No user is signed in
        }
        lista.setOnTouchListener(OnTouchListener { v, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN ->                 // Disallow ScrollView to intercept touch events.
                    v.parent.requestDisallowInterceptTouchEvent(true)

                MotionEvent.ACTION_UP ->                 // Allow ScrollView to intercept touch events.
                    v.parent.requestDisallowInterceptTouchEvent(false)
            }

            // Handle ListView touch events.
            v.onTouchEvent(event)
            true
        })
        email = findViewById(R.id.textView4)
        email.text = userEmail

        backbutton.setOnClickListener {
            finish()
        }
        
        button2.setOnClickListener{
            val intent = Intent(this, passwordreset::class.java)
            startActivity(intent)
        }

        var itemsAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lista_usuarios)
    }

    fun updateUsers(){
        Firebase.firestore.collection("Usuarios")
            .get()
            .addOnSuccessListener { db ->
                for(document in db){
                    Log.d("Aqui", "updateUsers: ${document.data["correo"]}")
                    lista_usuarios.add(document.data["correo"].toString())
                }
                val itemsAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lista_usuarios)
                lista.adapter = itemsAdapter
                Toast.makeText(this, "finalizó", Toast.LENGTH_LONG).show()
            }

    }
}
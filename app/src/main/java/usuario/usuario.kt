package usuario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a3dlab.R
import com.google.firebase.auth.FirebaseAuth
import Login.passwordreset
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore


class usuario : AppCompatActivity() {
    private lateinit var email:TextView
    private var userEmail:String = ""
    private lateinit var backbutton: ImageButton
    private lateinit var button2: Button
    private lateinit var lista: ListView
    private lateinit var swipe: SwipeRefreshLayout
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
        swipe = findViewById(R.id.swipeswipe)
        swipe.setColorSchemeResources(R.color.buttons)
        swipe.setOnRefreshListener {
            updateUsers()
            swipe.isRefreshing = false
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
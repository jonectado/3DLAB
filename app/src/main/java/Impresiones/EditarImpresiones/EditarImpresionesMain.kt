package Impresiones.EditarImpresiones

import Impresiones.Adaptador.AdaptadorImpresiones
import Impresiones.Clases.Impresion
import android.content.ContentValues.TAG
import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage

class EditarImpresionesMain : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var sendButton: Button
    private lateinit var chooseFiament: Button
    private lateinit var data:EditText
    private lateinit var image:ImageView
    private lateinit var chooseImage: Button

    private lateinit var lista_filamentos: Array<String>
    private var seleccion = 0
    private val db = Firebase.firestore
    private var photodb = Firebase.storage

    private var name:String = ""
    private var description:String = ""
    private var filament:String = ""
    private var weight:String = ""
    private var cost:String = ""
    private var photoID:String = ""
    private var id:String = ""
    private var uri:Uri? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_impresiones_main)

        db.collection("Filamentos")
            .get()
            .addOnSuccessListener { result ->
                lista_filamentos = arrayOf()
                for (document in result) {
                    var archivo: String = document.data["marca"].toString()+" "+document.data["color"].toString()
                    lista_filamentos= lista_filamentos.plus(archivo)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        photodb = FirebaseStorage.getInstance()

        sendButton = findViewById<Button>(R.id.button)
        sendButton.setOnClickListener{
            //Guarda el nombre
            data = findViewById<EditText>(R.id.nombre)
            name = data.text.toString()
            //Guarda la descripcion
            data = findViewById<EditText>(R.id.descripcion)
            description = data.text.toString()
            //Guarda el peso
            data = findViewById<EditText>(R.id.peso)
            weight = data.text.toString()
            //Sube la imagen a la Base de datos
            photodb.getReference("Impresiones").child(name)
                .putFile(uri!!)
                .addOnSuccessListener { task ->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener {
                            Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
                            photoID = "$it"
                        }
                }
            //Crea el documento
            if(name.isEmpty() || description.isEmpty() || weight.isEmpty() || filament.isEmpty()){
                Toast.makeText(this,"Rellene todos los campos para continuar",Toast.LENGTH_SHORT).show()
            }else {
                val print = Impresion(name, description, filament, weight, cost, photoID, id)
                // Nuevo documento con ID generada
                db.collection("Impresiones") //A que coleccion va a ir
                    .add(print) //envia el objeto que creamos arriba
                    .addOnSuccessListener { documentReference -> //Si es exitoso, muestra en la pestaña Logcat
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e -> //Si no es exitoso, muestra en la pestaña LogCat
                        Log.w(TAG, "Error adding document", e)
                    }
                //Solo para verlo en la app que si sirve
                Toast.makeText(this, "Añadido correctamente", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener{
            finish()
        }

        chooseFiament = findViewById<Button>(R.id.selectFilament)
        chooseFiament.setOnClickListener{
            val builderSingle = AlertDialog.Builder(this)
            builderSingle.setTitle("Eliga el filamento usado")
            builderSingle.setPositiveButton(getString(android.R.string.ok)){ dialog, _ -> dialog.dismiss()}
            builderSingle.setSingleChoiceItems(lista_filamentos, seleccion) {dialog, whitch ->
                seleccion = whitch
                chooseFiament.text = lista_filamentos[seleccion]
                filament = lista_filamentos[seleccion]
            }
            builderSingle.show()
        }
        chooseFiament = findViewById<Button>(R.id.selectFilament)

        image = findViewById<ImageView>(R.id.imageViewI)
        chooseImage = findViewById<Button>(R.id.chooseImage)

        val galleryImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                image.setImageURI(it)
                uri = it
            }
        )

        chooseImage.setOnClickListener{
            galleryImage.launch("image/*")
        }

    }
}
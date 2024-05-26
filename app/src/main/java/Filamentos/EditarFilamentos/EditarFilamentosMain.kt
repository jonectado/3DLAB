package Filamentos.EditarFilamentos

import Filamentos.Clases.Filamento
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage

class EditarFilamentoMain : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var sendButton: Button
    private lateinit var data: EditText
    private lateinit var image: ImageView
    private lateinit var chooseImage: Button
    private lateinit var titulo: TextView
    private lateinit var tipo: Button
    private val db: FirebaseFirestore = Firebase.firestore
    private var photodb: FirebaseStorage = FirebaseStorage.getInstance()
    private var seleccion: Int = 0
    private var color: String = ""
    private var marca: String = ""
    private var precio: String = ""
    private var inicial: String = ""
    private var restante: String = ""
    private var costoXgr: String = ""
    private var tipo_material: String = ""
    private var photoID: String = ""
    private var id: String = ""
    private val tipos_filamentos = arrayOf(
        "PLA",
        "ABS",
        "PET",
        "PETG",
        "Nylon",
        "HIPS",
        "TPU",
        "TPE",
        "Fibra de Carbono",
        "PP Polipropileno"
    )
    private var uri: Uri? = null
    private val galleryImage: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent(), ActivityResultCallback {
            image.setImageURI(it)
            uri = it
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_fmain)

        backButton = findViewById<ImageButton>(R.id.backButton)
        sendButton = findViewById<Button>(R.id.button)
        tipo = findViewById<Button>(R.id.Tipo)
        image = findViewById<ImageView>(R.id.imageViewF)
        chooseImage = findViewById<Button>(R.id.chooseImageF)
        titulo = findViewById<TextView>(R.id.title)

        val bundle = intent.extras
        val idDoc = bundle!!.getString("id")

        Firebase.firestore.collection("Filamentos").document(idDoc!!).get().addOnSuccessListener {
            color = it.data!!["color"].toString()
            findViewById<EditText>(R.id.C).setText(color)
            marca = it.data!!["marca"].toString()
            findViewById<EditText>(R.id.descripcion).setText(marca)
            precio = it.data!!["costo"].toString()
            findViewById<EditText>(R.id.selectFilament).setText(precio)
            inicial = it.data!!["inicial"].toString()
            findViewById<EditText>(R.id.FIni).setText(inicial)
            restante = it.data!!["restante"].toString()
            findViewById<EditText>(R.id.Res).setText(restante)
            tipo_material = it.data!!["tipo_material"].toString()
            tipo.text = tipo_material
            seleccion = tipos_filamentos.indexOf(tipo_material)
            photoID = it.data!!["foto"].toString()
            Glide.with(this).load(photoID).into(image)
            id = idDoc.toString()
        }



        sendButton.text = "Confirmar"
        titulo.text = "Editar filamento"

        sendButton.setOnClickListener {
            Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show()
            sendItem()
        }

        backButton.setOnClickListener {
            finish()
        }

        chooseImage.setOnClickListener {
            galleryImage.launch("image/*")
        }

        tipo.setOnClickListener {
            showTypes()
        }

    }


    private fun sendItem() {
        //Guarda el color
        data = findViewById<EditText>(R.id.C)
        color = data.text.toString()
        //Guarda la marca
        data = findViewById<EditText>(R.id.descripcion)
        marca = data.text.toString()
        //Guarda el precio
        data = findViewById<EditText>(R.id.selectFilament)
        precio = data.text.toString()
        //Verifica el peso inicial
        data = findViewById<EditText>(R.id.FIni)
        inicial = data.text.toString()
        //Verifica el peso restante
        data = findViewById<EditText>(R.id.Res)
        restante = data.text.toString()
        //Coloca el precio por Gramo de filamento
        costoXgr = (precio.toInt() / inicial.toInt()).toString()
        //Crea el documento
        if (color.isEmpty() || marca.isEmpty() || precio.isEmpty() || inicial.isEmpty() || (uri == null && photoID.isEmpty())  || restante.isEmpty()) {
            Toast.makeText(this, "Rellene todos los campos para continuar", Toast.LENGTH_SHORT)
                .show()
        } else {
            if (uri == null) {
                val print = Filamento(
                    color,
                    marca,
                    precio,
                    costoXgr,
                    inicial,
                    restante,
                    photoID,
                    tipo_material,
                    "Disponible",
                    id
                )
                db.collection("Filamentos") //A que coleccion va a ir
                    .document(id).set(print)//envia el objeto que creamos arriba
                    .addOnSuccessListener { documentReference -> //Si es exitoso, muestra en la pestaña Logcat
                        Log.d(
                            ContentValues.TAG, "DocumentSnapshot added with ID: ${id}"
                        )

                    }
                    .addOnFailureListener { e -> //Si no es exitoso, muestra en la pestaña LogCat
                        Log.w(
                            ContentValues.TAG, "Error adding document", e
                        )
                    }

                Toast.makeText(this, "Añadido correctamente", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                photodb.getReference("Impresiones").child("${color} ${marca}")
                    .putFile(uri!!)
                    .addOnSuccessListener { task ->
                        task.metadata!!.reference!!.downloadUrl
                            .addOnSuccessListener {
                                photoID = "$it"
                                val print = Filamento(
                                    color,
                                    marca,
                                    precio,
                                    costoXgr,
                                    inicial,
                                    restante,
                                    photoID,
                                    tipo_material,
                                    "Disponible",
                                    id
                                )
                                db.collection("Filamentos")
                                    .document(id)
                                    .delete()
                                    .addOnSuccessListener { _ ->
                                        db.collection("Filamentos") //A que coleccion va a ir
                                            .document(id)
                                            .set(print)//envia el objeto que creamos arriba
                                            .addOnSuccessListener { documentReference -> //Si es exitoso, muestra en la pestaña Logcat
                                                Log.d(
                                                    ContentValues.TAG,
                                                    "DocumentSnapshot added with ID: ${id}"
                                                )
                                            }
                                            .addOnFailureListener { e -> //Si no es exitoso, muestra en la pestaña LogCat
                                                Log.w(
                                                    ContentValues.TAG,
                                                    "Error adding document",
                                                    e
                                                )
                                            }
                                    }

                            }
                        //Solo para verlo en la app que si sirve
                        Toast.makeText(this, "Añadido correctamente", Toast.LENGTH_SHORT).show()
                        finish()
                    }
            }
        }
    }

    private fun showTypes() {
        val builderSingle = AlertDialog.Builder(this)
        builderSingle.setTitle("Eliga el estado de su impresion")
        builderSingle.setPositiveButton(getString(android.R.string.ok)) { dialog, _ -> dialog.dismiss() }
        builderSingle.setSingleChoiceItems(
            tipos_filamentos, seleccion
        ) { dialog, whitch ->
            seleccion = whitch
            tipo.text = tipos_filamentos[seleccion]
            tipo_material = tipos_filamentos[seleccion]
        }
        builderSingle.show()
    }
}
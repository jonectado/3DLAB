package Impresiones.EditarImpresiones

import Impresiones.Clases.Impresion
import android.annotation.SuppressLint
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
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Calendar

@Suppress("DEPRECATION")
class EditarImpresionMain : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var sendButton: Button
    private lateinit var chooseFiament: Button
    private lateinit var chooseStatus: Button
    private lateinit var data: EditText
    private lateinit var image: ImageView
    private lateinit var chooseImage: Button
    private lateinit var lista_precios: Array<Int>
    private lateinit var lista_filamentos: Array<String>
    private var seleccion: Int = 0
    private var seleccion2: Int = 0
    private val db: FirebaseFirestore = Firebase.firestore
    private var photodb: FirebaseStorage = FirebaseStorage.getInstance()
    private var name: String = ""
    private var description: String = ""
    private var filament: String = ""
    private var status: String = ""
    private var weight: String = ""
    private var cost: String = ""
    private var photoID: String = ""
    private var id: String = ""
    private var uri: Uri? = null
    private val galleryImage: ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                image.setImageURI(it)
                uri = it
            }
        )

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_impresion_main)

        initFilaments()

        val bundle = intent.extras
        val idDoc = bundle!!.getString("id")

        backButton = findViewById<ImageButton>(R.id.backButton)
        sendButton = findViewById<Button>(R.id.button)
        chooseFiament = findViewById<Button>(R.id.selectFilament)
        chooseStatus = findViewById<Button>(R.id.button6)
        image = findViewById<ImageView>(R.id.imageViewI)
        chooseImage = findViewById<Button>(R.id.chooseImage)
        id = idDoc!!

        Firebase.firestore.collection("Impresiones").document(idDoc).get().addOnSuccessListener {
            name = it.data!!["titulo"].toString()
            findViewById<EditText>(R.id.nombre).setText(name)
            description = it.data!!["descripcion"].toString()
            findViewById<EditText>(R.id.descripcion).setText(description)
            filament = it.data!!["filamentoUsado"].toString()
            chooseFiament.text = filament
            status = it.data!!["status"].toString()
            chooseStatus.text = status
            when (status) {
                "Completada" -> {
                    chooseStatus.background = resources.getDrawable(R.drawable.boton_completada)
                }

                "En proceso" -> {
                    chooseStatus.background = resources.getDrawable(R.drawable.boton_en_proceso)
                }

                "Fallida" -> {
                    chooseStatus.background = resources.getDrawable(R.drawable.boton_fallida)
                }
            }
            weight = it.data!!["gr_material"].toString()
            findViewById<EditText>(R.id.peso).setText(weight)
            photoID = it.data!!["foto"].toString()
            Glide.with(this).load(photoID).into(image)
        }

        sendButton.setOnClickListener {
            Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show()
            UpdateItem()
        }

        backButton.setOnClickListener {
            finish()
        }

        chooseFiament.setOnClickListener {
            showFilaments()
        }

        chooseImage.setOnClickListener {
            galleryImage.launch("image/*")
        }

        chooseStatus.setOnClickListener {
            showStatus()
        }

    }


    private fun showFilaments() {
        val builderSingle = AlertDialog.Builder(this)
        builderSingle.setTitle("Eliga el filamento usado")
        builderSingle.setPositiveButton(getString(android.R.string.ok)) { dialog, _ -> dialog.dismiss() }
        builderSingle.setSingleChoiceItems(lista_filamentos, seleccion) { dialog, whitch ->
            seleccion = whitch
            chooseFiament.text = lista_filamentos[seleccion]
            filament = lista_filamentos[seleccion]
        }
        builderSingle.show()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showStatus() {
        val builderSingle = AlertDialog.Builder(this)
        builderSingle.setTitle("Eliga el estado de su impresion")
        builderSingle.setPositiveButton(getString(android.R.string.ok)) { dialog, _ -> dialog.dismiss() }
        builderSingle.setSingleChoiceItems(
            arrayOf("Completada", "En proceso", "Fallida"),
            seleccion2
        ) { dialog, whitch ->
            seleccion2 = whitch
            chooseStatus.text = arrayOf("Completada", "En proceso", "Fallida")[seleccion2]
            status = arrayOf("Completada", "En proceso", "Fallida")[seleccion2]
            when (seleccion2) {
                0 -> {
                    chooseStatus.background = resources.getDrawable(R.drawable.boton_completada)
                }

                1 -> {
                    chooseStatus.background = resources.getDrawable(R.drawable.boton_en_proceso)
                }

                2 -> {
                    chooseStatus.background = resources.getDrawable(R.drawable.boton_fallida)
                }
            }
        }
        builderSingle.show()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun UpdateItem() {
        //Guarda el nombre
        data = findViewById<EditText>(R.id.nombre)
        name = data.text.toString()
        //Guarda la descripcion
        data = findViewById<EditText>(R.id.descripcion)
        description = data.text.toString()
        //Guarda el peso
        data = findViewById<EditText>(R.id.peso)
        weight = data.text.toString()
        //Verifica el precio
        if (weight.isNotEmpty()) {
            cost = (weight.toInt() * (lista_precios[seleccion] / 1000)).toString()
        }
        //Crea el documento
        if (name.isEmpty() || description.isEmpty() || weight.isEmpty() || filament.isEmpty() || (uri == null && photoID == "") || status.isEmpty()) {
            Toast.makeText(this, "Rellene todos los campos para continuar", Toast.LENGTH_SHORT)
                .show()
        } else {
            if (uri == null) {
                val print = Impresion(
                    name,
                    description,
                    filament,
                    weight,
                    cost,
                    photoID,
                    id,
                    SimpleDateFormat.getDateInstance().format(
                        Calendar.getInstance().time
                    ),
                    status
                )
                db.collection("Impresiones")
                    .document(id)
                    .delete()
                    .addOnSuccessListener { _ ->
                        db.collection("Impresiones") //A que coleccion va a ir
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
                Toast.makeText(this, "Añadido correctamente", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                photodb.getReference("Impresiones").child(name)
                    .putFile(uri!!)
                    .addOnSuccessListener { task ->
                        task.metadata!!.reference!!.downloadUrl
                            .addOnSuccessListener {
                                photoID = "$it"
                                val print = Impresion(
                                    name,
                                    description,
                                    filament,
                                    weight,
                                    cost,
                                    photoID,
                                    id,
                                    SimpleDateFormat.getDateInstance().format(
                                        Calendar.getInstance().time
                                    ),
                                    status
                                )
                                db.collection("Impresiones")
                                    .document(id)
                                    .delete()
                                    .addOnSuccessListener { _ ->
                                        db.collection("Impresiones") //A que coleccion va a ir
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

    private fun initFilaments() {
        lista_filamentos = arrayOf()
        lista_precios = arrayOf()
        db.collection("Filamentos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var archivo: String =
                        document.id + ". " + document.data["marca"].toString() + " " + document.data["color"].toString()
                    lista_filamentos = lista_filamentos.plus(archivo)
                    lista_precios = lista_precios.plus(document.data["costo"].toString().toInt())
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}
package Impresiones.AnadirImpresiones

import Impresiones.Clases.Impresion
import Notificaciones.Clases.Notificacion
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage

class AnadirImpresionesMain : AppCompatActivity() {

    private lateinit var lista_pesos: Array<Int>
    private lateinit var backButton: ImageButton
    private lateinit var sendButton: Button
    private lateinit var chooseFiament: Button
    private lateinit var chooseStatus: Button
    private lateinit var data: EditText
    private lateinit var image: ImageView
    private lateinit var chooseImage: Button
    private lateinit var id_impresiones: Array<Int>
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anadir_impresiones_main)

        initFilaments()
        initId()

        backButton = findViewById<ImageButton>(R.id.backButton)
        sendButton = findViewById<Button>(R.id.button)
        chooseFiament = findViewById<Button>(R.id.selectFilament)
        chooseStatus = findViewById<Button>(R.id.button6)
        image = findViewById<ImageView>(R.id.imageViewI)
        chooseImage = findViewById<Button>(R.id.chooseImage)

        sendButton.setOnClickListener {
            Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show()
            sendItem()
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

    private fun initId() {
        id_impresiones = arrayOf()
        db.collection("Impresiones")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    id_impresiones = id_impresiones.plus(document.id.toInt())
                }
                id = if (id_impresiones.isEmpty()) {
                    "1"
                } else {
                    println("Okey")
                    (id_impresiones.max() + 1).toString()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
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
    private fun sendItem() {
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
            cost = (weight.toInt() * lista_precios[seleccion]).toString()
        }
        //Crea el documento
        if (name.isEmpty() || description.isEmpty() || weight.isEmpty() || filament.isEmpty() || uri == null || status.isEmpty()) {
            Toast.makeText(this, "Rellene todos los campos para continuar", Toast.LENGTH_SHORT)
                .show()
        } else if (lista_pesos[seleccion] < weight.toInt()) {
            Toast.makeText(
                this,
                "No tiene suficiente filamento de este tipo para la impresión.",
                Toast.LENGTH_SHORT
            ).show()
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
                            db.collection("Impresiones") //A que coleccion va a ir
                                .document(id)
                                .set(print)//envia el objeto que creamos arriba
                                .addOnSuccessListener { documentReference -> //Si es exitoso, muestra en la pestaña Logcat
                                    Log.d(
                                        TAG,
                                        "DocumentSnapshot added with ID: ${id}"
                                    )
                                    // Aqui va el codigo para notificaciones Y la variable que necesitas es Weight

                                }
                                .addOnFailureListener { e -> //Si no es exitoso, muestra en la pestaña LogCat
                                    Log.w(
                                        TAG,
                                        "Error adding document",
                                        e
                                    )
                                }
                        }
                    //Solo para verlo en la app que si sirve
                    Toast.makeText(this, "Añadido correctamente", Toast.LENGTH_SHORT).show()
                    db.collection("Filamentos")
                        .document("${seleccion+1}")
                        .update("restante","${lista_pesos[seleccion] - weight.toInt()}")
                        .addOnSuccessListener {
                            Log.w(
                                ContentValues.TAG,
                                "FUNCIONA PERO NO HACE NADA"
                            )
                        }
                    finish()
                }
        }
    }

    private fun noti() {
        db.collection("Impresiones")
            .get()
            .addOnSuccessListener { result ->
                val count = result.size()
                var notificacion_mostrar = ""
                if(count > 8){
                    notificacion_mostrar = "ejes"
                }
                if(count > 10){
                    notificacion_mostrar = "cama"
                }

                saveNotification(notificacion_mostrar, "0")

                db.collection("Notificaciones")
                    .document("num_impresiones")
                    .set(mapOf("count" to count))
            }
    }

    private fun saveNotification(notificacion_text: String, mostrar: String) {
        val notificacion = Notificacion( notificacion_text, mostrar)
        db.collection("Notifications")
            .add(notificacion)
            .addOnSuccessListener {
                Log.d(TAG, "Notification saved")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error saving notification", e)
            }
    }

    private fun initFilaments() {
        lista_filamentos = arrayOf()
        lista_precios = arrayOf()
        lista_pesos = arrayOf()
        db.collection("Filamentos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data["estado"] != "No disponible") {
                        val archivo: String =
                            document.id + ". " + document.data["marca"].toString() + " " + document.data["color"].toString()
                        lista_filamentos = lista_filamentos.plus(archivo)
                        lista_precios =
                            lista_precios.plus(document.data["costoXgr"].toString().toInt())
                        lista_pesos = lista_pesos.plus(document.data["restante"].toString().toInt())
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}
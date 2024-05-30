import Impresiones.Clases.Impresion
import Impresiones.AnadirImpresiones.AnadirImpresionesMain
import Impresiones.EditarImpresiones.EditarImpresionMain
import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a3dlab.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class ViewHolderImpresiones(view: View) : RecyclerView.ViewHolder(view) {
    private val nombre: TextView = view.findViewById<TextView>(R.id.nombre)
    private val descripcion: TextView = view.findViewById<TextView>(R.id.descripcion)
    private val precio: TextView = view.findViewById<TextView>(R.id.precio)
    private val restante: TextView = view.findViewById<TextView>(R.id.restante)
    private val marca: TextView = view.findViewById<TextView>(R.id.marca)
    private val foto: ImageView = view.findViewById<ImageView>(R.id.ImagenI)
    private val status: Button = view.findViewById<Button>(R.id.status)
    private val editar: Button = view.findViewById<Button>(R.id.edit)

    @SuppressLint("SetTextI18n")
    fun render(impresion: Impresion) {
        nombre.text = impresion.titulo
        descripcion.text = impresion.descripcion
        precio.text = "$ ${impresion.valor}"
        restante.text = "${impresion.gr_material} gr"
        marca.text = impresion.filamentoUsado
        status.text = impresion.status
        Glide.with(nombre.context).load(impresion.foto).into(foto)
        when (impresion.status) {
            "Completada" -> {
                status.setBackgroundResource(R.drawable.boton_completada)
            }

            "En proceso" -> {
                status.setBackgroundResource(R.drawable.boton_en_proceso)
            }

            "Fallida" -> {
                status.setBackgroundResource(R.drawable.boton_fallida)
            }
        }
        editar.setOnClickListener {
            val intent = Intent(
                editar.context,
                EditarImpresionMain::class.java
            )
            val str: String = impresion.id.toString()
            intent.putExtra("id", str)
            startActivity(editar.context, intent, bundleOf())
        }
        status.setOnClickListener {
            val builderSingle = AlertDialog.Builder(status.context)
            builderSingle.setTitle("Eliga el estado de su impresion")
            builderSingle.setPositiveButton("ok") { dialog, _ -> dialog.dismiss() }
            var seleccion2 = 0
            builderSingle.setSingleChoiceItems(
                arrayOf("Completada", "En proceso", "Fallida"),
                seleccion2
            ) { dialog, whitch ->
                seleccion2 = whitch
                status.text = arrayOf("Completada", "En proceso", "Fallida")[seleccion2]
                when (seleccion2) {
                    0 -> {
                        status.setBackgroundResource(R.drawable.boton_completada)
                        Firebase.firestore.collection("Impresiones").document(impresion.id.toString())
                            .update("status", "Completada")
                    }

                    1 -> {
                        status.setBackgroundResource(R.drawable.boton_en_proceso)
                        Firebase.firestore.collection("Impresiones").document(impresion.id.toString())
                            .update("status", "En proceso")
                    }

                    2 -> {
                        status.setBackgroundResource(R.drawable.boton_fallida)
                        Firebase.firestore.collection("Impresiones").document(impresion.id.toString())
                            .update("status", "Fallida")
                    }
                }
            }
            builderSingle.show()
        }
    }
}
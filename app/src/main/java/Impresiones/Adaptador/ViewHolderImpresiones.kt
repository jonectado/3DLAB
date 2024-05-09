import Impresiones.Clases.Impresion
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a3dlab.R

class ViewHolderImpresiones(view: View):RecyclerView.ViewHolder(view) {
    private val nombre: TextView = view.findViewById<TextView>(R.id.nombre)
    private val descripcion: TextView = view.findViewById<TextView>(R.id.descripcion)
    private val precio: TextView = view.findViewById<TextView>(R.id.precio)
    private val restante: TextView = view.findViewById<TextView>(R.id.restante)
    private val marca: TextView = view.findViewById<TextView>(R.id.marca)
    private val foto: ImageView = view.findViewById<ImageView>(R.id.ImagenI)
    fun render(impresion: Impresion){
        nombre.text = impresion.titulo
        descripcion.text = impresion.descripcion
        precio.text = "$ ${impresion.valor}"
        restante.text = "${impresion.gr_material} gr"
        marca.text = impresion.filamentoUsado


        Glide.with(nombre.context).load(impresion.foto).into(foto)
    }
}
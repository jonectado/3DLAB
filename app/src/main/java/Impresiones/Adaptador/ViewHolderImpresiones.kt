import Impresiones.Clases.Impresion
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.DashBoard.R

class ViewHolderImpresiones(view: View):RecyclerView.ViewHolder(view) {
    val nombre = view.findViewById<TextView>(R.id.nombre)
    val descripcion = view.findViewById<TextView>(R.id.descripcion)
    val precio = view.findViewById<TextView>(R.id.precio)
    val restante = view.findViewById<TextView>(R.id.restante)
    val marca = view.findViewById<TextView>(R.id.marca)
    val foto = view.findViewById<ImageView>(R.id.ImagenI)
    fun render(impresion: Impresion){
        nombre.text = impresion.titulo
        descripcion.text = impresion.descripcion
        precio.text = "$ ${impresion.valor}"
        restante.text = "${impresion.gr_material} gr"
        marca.text = impresion.filamentoUsado


        Glide.with(nombre.context).load(impresion.foto).into(foto)
    }
}
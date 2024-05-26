package Filamentos

import Filamentos.Adaptador.AdaptadorFilamentos
import Filamentos.AnadirFilamentos.AnadirFilamentosMain
import Filamentos.Clases.Filamento
import Impresiones.Adaptador.AdaptadorImpresiones
import Impresiones.Clases.Impresion
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.a3dlab.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FilamentosMain : AppCompatActivity() {
    private lateinit var backButton :ImageButton
    private lateinit var addButton: FloatingActionButton
    private val db = Firebase.firestore
    private var listaFilamentos : ArrayList<Filamento> = ArrayList<Filamento>()
    private lateinit var searchBar: SearchView
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var recyclerview:RecyclerView
    private lateinit var adapter: AdaptadorFilamentos
    private lateinit var chooseMode: ImageButton
    private var seleccion: Int = 0
    private var mode: String = "Color"
    private var lista_: Array<String> =arrayOf("Color", "Marca", "Peso", "Precio", "Tipo")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filamentos_main)
        initRecyclerView()
        backButton = findViewById(R.id.backButtonI)
        addButton = findViewById(R.id.anadir)
        swipe = findViewById(R.id.swiper)
        recyclerview = findViewById(R.id.lista)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = AdaptadorFilamentos(listaFilamentos)
        backButton.setOnClickListener{
            finish()
        }
        
        addButton.setOnClickListener{
            val intent = Intent(this, AnadirFilamentosMain::class.java)
            startActivity(intent)
            initRecyclerView()
        }

        swipe.setColorSchemeResources(R.color.buttons)
        swipe.setOnRefreshListener {
            initRecyclerView()
            swipe.isRefreshing = false
        }
        
        searchBar = findViewById(R.id.searchView)
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                Log.w(ContentValues.TAG, "${p0==""}")
                searchlist(p0)
                return true
            }

        })
        searchBar.queryHint = "Buscando por: "+ lista_[seleccion]
        chooseMode = findViewById<androidx.appcompat.widget.AppCompatImageButton>(R.id.button4)
        chooseMode.setOnClickListener{
            val builderSingle = AlertDialog.Builder(this)
            builderSingle.setTitle("Buscar por: ")
            builderSingle.setPositiveButton(getString(android.R.string.ok)){ dialog, _ -> dialog.dismiss()}
            builderSingle.setSingleChoiceItems(lista_, seleccion) {dialog, whitch ->
                seleccion = whitch
                mode = lista_[seleccion]
                searchBar.queryHint = "Buscando por: "+ lista_[seleccion]
            }
            builderSingle.show()
        }
    }
    override fun onResume() {
        listaFilamentos.clear()
        super.onResume()
        println("si")
        this.initRecyclerView()
    }

    fun searchlist(text:String){
        val searchlist = ArrayList<Filamento>()
        if(text == ""){
            searchDataList(listaFilamentos)
        }else{
            when(mode){
                "Color" -> {
                    for (impresion in listaFilamentos) {
                        if (impresion.color.lowercase().contains(text.lowercase())) {
                            searchlist.add(impresion)
                        }
                    }
                }
                "Restante"->{
                    for (impresion in listaFilamentos){
                        if(impresion.restante.lowercase().contains(text.lowercase())) {
                            searchlist.add(impresion)
                        }
                    }
                }
                "Precio"->{
                    for (impresion in listaFilamentos){
                        if(impresion.costo.lowercase().contains(text.lowercase())) {
                            searchlist.add(impresion)
                        }
                    }
                }
                "Marca"->{
                    for (impresion in listaFilamentos){
                        if(impresion.marca.lowercase().contains(text.lowercase())) {
                            searchlist.add(impresion)
                        }
                    }
                }
                "Tipo"->{
                    for (impresion in listaFilamentos){
                        if(impresion.marca.lowercase().contains(text.lowercase())) {
                            searchlist.add(impresion)
                        }
                    }
                }
            }
            searchDataList(searchlist)
        }
    }

    fun searchDataList(searchList:ArrayList<Filamento>){
        adapter = AdaptadorFilamentos(searchList)
        recyclerview.adapter = adapter
    }

    private fun initRecyclerView() {
        listaFilamentos.clear()
        db.collection("Filamentos")
            .get()
            .addOnSuccessListener { result ->
                listaFilamentos.clear()
                for (document in result) {
                    var archivo: Filamento = Filamento(document.data["color"].toString(),
                        document.data["marca"].toString(),
                        document.data["costo"].toString(),
                        document.data["costoXgr"].toString(),
                        document.data["inicial"].toString(),
                        document.data["restante"].toString(),
                        document.data["foto"].toString(),
                        document.data["tipo_material"].toString(),
                        document.data["estado"].toString(),
                        document.data["id"].toString())
                    listaFilamentos.add(archivo)
                }
                if(listaFilamentos.isEmpty()){
                    Toast.makeText(this, "Añade tu primer filamento!", Toast.LENGTH_SHORT).show()
                }else{
                    listaFilamentos.sortBy { "id" }
                    listaFilamentos.reverse()
                    recyclerview.layoutManager = GridLayoutManager(this,2)
                    recyclerview.adapter = AdaptadorFilamentos(listaFilamentos)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
                recyclerview.layoutManager = LinearLayoutManager(this)
                var listaImpresionesNull= listOf(Impresion("No","Funciona","esto", "Aggggg","F","","Aggggg", "Ffffff","aaa"))
                recyclerview.adapter = AdaptadorImpresiones(listaImpresionesNull)
            }

    }
}
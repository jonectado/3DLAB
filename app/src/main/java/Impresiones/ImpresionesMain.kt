package Impresiones

import Impresiones.Adaptador.AdaptadorImpresiones
import Impresiones.Clases.Impresion
import Impresiones.AnadirImpresiones.AnadirImpresionesMain
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.a3dlab.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore



class ImpresionesMain : AppCompatActivity() {
    private lateinit var backButton :ImageButton
    private lateinit var addButton: FloatingActionButton
    private val db = Firebase.firestore
    private var listaImpresiones : ArrayList<Impresion> = ArrayList<Impresion>()
    private lateinit var searchBar: SearchView
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var recyclerview:RecyclerView
    private lateinit var adapter: AdaptadorImpresiones
    private lateinit var chooseMode: ImageButton
    private var seleccion: Int = 0
    private var mode: String = ""
    private var lista_: Array<String> =arrayOf("Nombre", "Fecha (mm dd, yyyy)", "Peso", "Precio", "Filamento Usado")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_impresiones_main)
        initRecyclerView()
        backButton = findViewById(R.id.backButtonI)
        addButton = findViewById(R.id.anadir)
        swipe = findViewById(R.id.swiper)
        recyclerview = findViewById(R.id.lista)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = AdaptadorImpresiones(listaImpresiones)
        mode = "Nombre"
        backButton.setOnClickListener{
            finish()
        }

        addButton.setOnClickListener{
            val intent = Intent(this, AnadirImpresionesMain::class.java)
            startActivity(intent)
            initRecyclerView()
        }

        swipe.setColorSchemeResources(R.color.buttons)
        swipe.setOnRefreshListener {
            initRecyclerView()
            swipe.isRefreshing = false
        }

        searchBar = findViewById(R.id.searchView)
        searchBar.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                Log.w(TAG, "${p0==""}")
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
        listaImpresiones.clear()
        super.onResume()
        println("si")
        this.initRecyclerView()
    }

    fun searchlist(text:String){
        val searchlist = ArrayList<Impresion>()
        if(text == ""){
            searchDataList(listaImpresiones)
        }else{
            when(mode){
                "Nombre" -> {
                    for (impresion in listaImpresiones) {
                        if (impresion.titulo.lowercase().contains(text.lowercase())) {
                            searchlist.add(impresion)
                        }
                    }
                }

                "Fecha (mm dd, yyyy)"->{
                    for (impresion in listaImpresiones){
                        if(impresion.date.lowercase().contains(text.lowercase())) {
                            println(impresion.date.lowercase())
                            searchlist.add(impresion)
                        }
                    }
                }
                "Peso"->{
                    for (impresion in listaImpresiones){
                        if(impresion.gr_material.lowercase().contains(text.lowercase())) {
                            searchlist.add(impresion)
                        }
                    }
                }
                "Precio"->{
                    for (impresion in listaImpresiones){
                        if(impresion.valor.lowercase().contains(text.lowercase())) {
                            searchlist.add(impresion)
                        }
                    }
                }
                "Filamento Usado"->{
                    for (impresion in listaImpresiones){
                        if(impresion.filamentoUsado.lowercase().contains(text.lowercase())) {
                            searchlist.add(impresion)
                        }
                    }
                }
            }
            searchDataList(searchlist)
        }
    }

    fun searchDataList(searchList:ArrayList<Impresion>){
        adapter = AdaptadorImpresiones(searchList)
        recyclerview.adapter = adapter
    }

    private fun initRecyclerView() {
        listaImpresiones.clear()
        db.collection("Impresiones")
            .get()
            .addOnSuccessListener { result ->
                listaImpresiones.clear()
                for (document in result) {
                    var archivo: Impresion = Impresion(document.data["titulo"].toString(),
                        document.data["descripcion"].toString(),
                        document.data["filamentoUsado"].toString(),
                        document.data["gr_material"].toString(),
                        document.data["valor"].toString(),
                        document.data["foto"].toString(),
                        document.data["id"].toString(),
                        document.data["date"].toString(),
                        document.data["status"].toString())
                    listaImpresiones.add(archivo)
                }
                if(listaImpresiones.isEmpty()){
                    Toast.makeText(this, "Añade tu primera impresión!", Toast.LENGTH_SHORT).show()
                }else{
                    listaImpresiones.sortBy { "id" }
                    listaImpresiones.reverse()
                    recyclerview.layoutManager = LinearLayoutManager(this)
                    recyclerview.adapter = AdaptadorImpresiones(listaImpresiones)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                recyclerview.layoutManager = LinearLayoutManager(this)
                var listaImpresionesNull= listOf(Impresion("No","Funciona","esto", "Aggggg","F","","Aggggg", "Ffffff","aaa"))
                recyclerview.adapter = AdaptadorImpresiones(listaImpresionesNull)
            }

    }
}
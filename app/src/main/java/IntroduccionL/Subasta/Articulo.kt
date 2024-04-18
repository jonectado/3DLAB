package IntroduccionL.Subasta

import IntroduccionL.Subasta.Oferta

class Articulo {

    /*atributos*/
    private var nombre:String
    fun getnombre(): String {
        return nombre
    }
    private var tipo_articulo:String
    fun gettipo_articulo(): String {
        return tipo_articulo
    }
    private var precio_base:Double
    fun getprecio_base(): Double {
        return precio_base
    }

    var ofertas_articulo:MutableList<Oferta> = mutableListOf()

    constructor(nombre:String, tipo_articulo:String, precio_base:Double){
        this.nombre = nombre
        this.tipo_articulo = tipo_articulo
        this.precio_base = precio_base
        this.ofertas_articulo = ofertas_articulo
    }

    fun añadir_oferta (oferta: Oferta){
        this.ofertas_articulo.add(oferta)
    }
}
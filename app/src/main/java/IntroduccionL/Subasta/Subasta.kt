package IntroduccionL.Subasta

import IntroduccionL.Subasta.Articulo
import IntroduccionL.Subasta.Oferta

class Subasta {

    private var articulos_subastados: MutableList<Articulo>  = mutableListOf()

    fun getarticulos_subastados(): MutableList<Articulo> {
        return articulos_subastados
    }

    constructor()

    fun añadir_articulo(articulo: Articulo){
        this.articulos_subastados.add(articulo)
    }

    fun comparar_ofertas(articulo: Articulo): Double {

        var ofertas = articulo.ofertas_articulo
        var oferta_mayor = 0.0
        var comprador:String = ""


        if (ofertas.isEmpty() == true){
            oferta_mayor  = articulo.getprecio_base()
            comprador = "Casa subasta"
        }else{
            for (i in 0..ofertas.size -1){

                if (ofertas[i].oferta > oferta_mayor){
                    oferta_mayor = ofertas[i].oferta
                    comprador = ofertas[i].ofertante
                }
            }
        }
        return oferta_mayor
    }

    fun subastar (): MutableList<String> {

        var articulo_a_subastar = articulos_subastados
        var vender:MutableList<String> = mutableListOf()

        for (i in 0..articulo_a_subastar.size-1){

            var oferta_final = this.comparar_ofertas(articulo_a_subastar[i])
            //var comprador = this.comparar_ofertas(articulo_a_subastar[i])

            if (oferta_final == articulo_a_subastar[i].getprecio_base()){
                vender.add("Vendido a $oferta_final")
            }else if (oferta_final > articulo_a_subastar[i].getprecio_base()){
                var comprador = articulos_subastados[i].ofertas_articulo
                vender.add("vendido a $oferta_final")
            }
        }
        return vender
    }
}

fun main(){

    var articulo1:Articulo = Articulo("Mona Lisa", "Pintura", 4000000.0)
    var oferta1_articulo1:Oferta = Oferta("Pepe", 8500000.0)
    var oferta2_articulo1:Oferta = Oferta("Laura", 5000000.0)

    var articulo2:Articulo = Articulo("Venus de Milo", "Escultura", 15000000.0)
    var oferta1_articulo2:Oferta = Oferta("Pepe", 8500000.0)
    var oferta2_articulo2:Oferta = Oferta("Laura", 5000000.0)

    var subasta1:Subasta = Subasta()

    subasta1.añadir_articulo(articulo1)
    subasta1.añadir_articulo(articulo2)

    print(subasta1.subastar())

}
package IntroduccionL.Reproductor

import IntroduccionL.Reproductor.Cancion

class Album {

    private var canciones:MutableList<Cancion>  = mutableListOf()

    fun getcanciones():MutableList<Cancion>{
        return canciones
    }
    private var tipo_musica:String = "Todo"
    fun gettipo_musica():String{
        return tipo_musica
    }

    private var nombre:String = "album"

    fun getnombre(): String {
        return nombre
    }
    fun setnombre(nombre:String) {
        println("Ingrese un nombre para el album")
        this.nombre
    }

    constructor(nombre:String, tipo_musica:String){
        this.nombre
        this.tipo_musica
    }

    fun añadir_cancion(nueva_cancion: Cancion):String{
        this.canciones.add(nueva_cancion)
        return "Se añadio $(nueva_cancion.nombre)"
    }

    fun cuantas_canciones(): Int {
        var total_canciones = this.canciones.size
        return total_canciones
    }

    fun desc_album(): MutableList<String> {

        var msg:MutableList<String>  = mutableListOf()

        for (i in 0..canciones.size-1){

            var name = this.canciones[i].titulo
            var artista = this.canciones[i].artista
            var publicacion = this.canciones[i].año_publicacion
            var rep = this.canciones[i].reproducciones

            var añadir = "$name, interpretada por $artista, se lanzo en $publicacion, $rep"

            msg.add(añadir)
        }
        return msg
    }

    fun popular(): MutableList<String> {

        var popular:MutableList<String>  = mutableListOf()

        var añadir:String

        for (i in 0..canciones.size-1){

            if (this.canciones[i].reproducciones < 1000000){
                var name = this.canciones[i].titulo
                añadir = "$name : poco popular"
            }else{
                var name = this.canciones[i].titulo
                añadir = "$name : popular"
            }
            popular.add(añadir)
        }
        return popular

    }
}
fun main(){

    var album:Album = Album("Album1", "variada")
    var cancion1:Cancion = Cancion("Bones", "Imagine dragons",
        2022, 146213178
    )
    var cancion2:Cancion = Cancion("Mercy", "Shawn Mendez", 2018, 6689715)
    album.getnombre()
    album.añadir_cancion(cancion1)
    album.añadir_cancion(cancion2)
    println(album.cuantas_canciones())
    println(album.desc_album())
    println(album.popular())
}
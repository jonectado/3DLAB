package IntroduccionS.Taller_1.reproductor

import IntroduccionS.Taller_1.reproductor.Cancion
class Album {
    private var titulo: String = ""
    private var num_canciones: Int = 0
    private var genero: String = ""
    private var canciones: MutableList<Cancion> = mutableListOf()


    constructor()

    constructor(titulo: String, num_canciones: Int, genero: String, canciones: MutableList<Cancion>) : this() {
        this.titulo = titulo
        this.num_canciones = num_canciones
        this.genero = genero
        this.canciones = canciones
    }

    //Getters
    fun get_titulo():String{
        return this.titulo
    }
    fun get_num_canciones():Int{
        return this.num_canciones
    }
    fun get_genero():String{
        return this.genero
    }
    fun get_canciones():MutableList<Cancion>{
        return this.canciones
    }

    //Setters
    fun set_titulo(value:String){
        titulo = value
    }
    fun set_num_canciones(value:Int){
        num_canciones = value
    }
    fun set_genero(value:String){
        genero = value
    }
    fun set_canciones(value:MutableList<Cancion>){
        canciones = value
    }

    //Metodos
    fun pedirDatos(){
        println("Titulo: ")
        titulo = readln()
        println("Numero de canciones en el album: ")
        num_canciones = readln()!!.toInt()
        println("Genero: ")
        genero = readln()
    }

    fun analisis_canciones(){
        var mas_popular: String = "No hay una cancion con reproducciones"
        var max_reproduccion: Int = 0

        for (cancion in canciones){
            var titulo_cancion: String = cancion.get_titulo()
            var num_reproduccion: Int = cancion.get_reproducciones()
            var popular: String = "No"

            if(num_reproduccion>max_reproduccion){
                max_reproduccion = num_reproduccion
                mas_popular = titulo_cancion
            }
            if(num_reproduccion>1000){
                popular = "Si"
            }
            println("$titulo_cancion reproducciones:$num_reproduccion Popular: $popular")


        }
        println("La canción más popular es $mas_popular con $max_reproduccion reproducciones")
    }
    fun tracklist(){
        for(cancion in canciones){
            cancion.descripcion()
        }
    }
}

fun main(){
    var c1: Cancion = Cancion("Red","Taylor Swift",2011,1200)
    var c2: Cancion = Cancion("All too well","TS",2012,999)
    var c3: Cancion = Cancion("22","Tay Tay",2011,1000)

    var lista: MutableList<Cancion> = mutableListOf(c1,c2,c3)

    var red: Album = Album("red",3,"Pop",lista)
    red.tracklist()
    red.analisis_canciones()
}
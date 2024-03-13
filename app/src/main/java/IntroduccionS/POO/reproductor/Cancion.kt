package IntroduccionS.POO.reproductor

class Cancion {
    private var titulo:String =""
    private var artista:String =""
    private var year:Int = 0
    private var reproducciones:Int = 0

    //constructor
    constructor()

    //Getters
    fun get_titulo():String{
        return this.titulo
    }
    fun get_artista(): String {
        return this.artista
    }

    fun get_year(): Int {
        return this.year
    }

    fun get_reproducciones(): Int {
        return this.reproducciones
    }

    //Setters
    fun set_titulo(value:String){
        titulo = value
    }
    fun setArtista(value: String) {
        this.artista = value
    }

    fun set_year(value: Int) {
        this.year = value
    }

    fun set_reproducciones(value: Int) {
        this.reproducciones = value
    }

    //Metodos
    fun pedirDatos(){
        println("Titulo: ")
        titulo = readln()
        println("Artista: ")
        artista = readln()
        println("Año: ")
        year = readln()!!.toInt()
    }
}

fun main(){
    val hd=Cancion()
    hd.pedirDatos()
    println(hd.get_titulo())
    println(hd.get_artista())
    println(hd.get_year())
    println(hd.get_reproducciones())
}
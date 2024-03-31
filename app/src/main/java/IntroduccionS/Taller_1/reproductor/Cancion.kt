package IntroduccionS.Taller_1.reproductor


class Cancion{
    private var titulo:String =""
    private var artista:String =""
    private var year:Int = 0
    private var reproducciones:Int = 0

    //constructor
    constructor()

    constructor(titulo: String, artista: String, year: Int, reproducciones: Int) : this() {
        this.titulo = titulo
        this.artista = artista
        this.year = year
        this.reproducciones = reproducciones
    }
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

    fun descripcion(){
        println("$titulo interpretada por $artista, se lanzó en el año $year, cantidad de reproducciones: $reproducciones")
    }
}


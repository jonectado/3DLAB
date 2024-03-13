package IntroduccionN.POO.Reproductor

class Cancion {
    private var nombre: String
    private var artista: String
    private var year: Int
    private var reproducciones: Int
    private var popular:Boolean
    fun getReproducciones():Int{
        return reproducciones
    }
    fun getNombre():String{
        return nombre
    }
    constructor(nombre: String, artista: String, year: Int, reproducciones: Int) {
        this.nombre = nombre
        this.artista = artista
        this.year = year
        this.reproducciones = reproducciones
        if(reproducciones>=1000){
            this.popular = true
        }else{
            this.popular= false
        }
    }
    fun print(){
        if(popular){
            println("       $nombre, interpretada por $artista, se lanzó en $year y tiene $reproducciones reproducciones, es popular")
        }else{
            println("       $nombre, interpretada por $artista, se lanzó en $year y tiene $reproducciones reproducciones, no es popular")
        }
    }
}


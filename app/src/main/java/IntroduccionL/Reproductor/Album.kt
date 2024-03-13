package IntroduccionL.Reproductor

class Album {

    private var canciones:MutableList<Cancion>  = mutableListOf()

    fun getcanciones():MutableList{

    }
    private var tipo_musica:String = "Todo"
    fun gettipo_musica():String{
        return tipo_musica
    }

    var nombre:String
        get() {
            TODO()
        }
        set(value) {}

    constructor(nombre:String, tipo_musica:String){
        this.nombre
        this.tipo_musica
    }


}
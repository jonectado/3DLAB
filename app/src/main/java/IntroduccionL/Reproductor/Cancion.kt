package IntroduccionL.Reproductor

class Cancion {

    /*Atributos*/

    var titulo:String
    var artista:String
    var año_publicacion:Int
    var reproducciones:Int

    constructor(titulo:String, artista:String, año_publicacion:Int, reproduccion:Int) { //constructor con paramentros
        this.titulo = titulo
        this.artista = artista
        this.año_publicacion = año_publicacion
        this.reproducciones = reproduccion
    }
}
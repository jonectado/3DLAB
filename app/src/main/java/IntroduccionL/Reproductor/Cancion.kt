package IntroduccionL.Reproductor

class Cancion {

    /*Atributos*/

    var titulo:String
    var artista:String
    var año_publicacion:String
    var reproducciones:String

    constructor(titulo:String, artista:String, año_publicacion:String, reproduccion:String) { //constructor con paramentros
        this.titulo = titulo
        this.artista = artista
        this.año_publicacion = año_publicacion
        this.reproducciones = reproduccion
    }
}
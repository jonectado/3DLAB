package IntroduccionS.POO

class Persona {
    //Atributos
    private var nombre:String = ""
    fun get_nombre():String {
        return this.nombre
    }
    fun set(value:String) {
        nombre = value
    }
    var edad:Int
        get() {
            TODO()
        }
        set(value) {

        }
    var estatura:Double
        get() {
            TODO()
        }
        set(value) {

        }
    var sexo:Boolean
        get() {
            TODO()
        }
        set(value) {}

    constructor()

    /*constructor(nombre:String,edad:Int,estatura:Double,sexo:Boolean){
        this.nombre = nombre
        this.edad = edad
        this.estatura = estatura
        this.sexo = sexo
    }*/
    fun pedirDatos(){
        println("Por favor ingrese su nombre")
        nombre = readln()

    }

}

fun main(){
    var person1 = Persona()
    person1.pedirDatos()
    println(person1.get_nombre())
}
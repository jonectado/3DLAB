package IntroduccionN.POO

class Persona {
    /*Atributos*/
    private var nombre:String = ""
    fun getNombre():String{
        return nombre
    }
    private var edad:Int = 0
    fun getEdad():Int{
        return edad
    }
    fun setEdad(edad:Int){
        this.edad = edad
    }
    
    private var estatura:Double = 0.0
    fun getEstatura():Double{
        return estatura
    }
    private var sex:Boolean = false

    //Constructor con parametros
    /*constructor(nombre: String, edad:Int, estatura:Double, sex:Boolean) {
        this.nombre = nombre
        this.sex = sex
        this.edad = edad
        this.estatura = estatura
    }*/
    fun pedirDatos() {
        println("Por favor ingrese su nombre: ")
        nombre = readln()
    }
}

fun main() {
    val a:Persona = Persona()
    a.pedirDatos()
    println(a.getNombre())
    println(a.getEdad())
}
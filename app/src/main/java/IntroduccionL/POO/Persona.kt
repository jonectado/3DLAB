package IntroduccionL.POO

class Persona {

    /*Atributos*/

   var nombre:String
       get() {
           TODO()
       }
       set(value) {}

   var edad:Int
       get() {
           TODO()
       }
       set(value) {}
   var estatura:Double
       get() {
            TODO()
       }
       set(value) {}
   var sexo:Boolean
       get() {
            TODO()
       }
       set(value) {}


   //constructor()

   constructor(estatura: Double, sexo:Boolean, edad:Int, nombre:String) { //constructor con paramentros
       this.estatura = estatura
       this.sexo = sexo
       this.edad = edad
       this.nombre = nombre
   }

    fun pedir_datos(){
        println("Por favor ingrese su nombre")
        this.nombre
        println("Por favor ingrese su edad")
        this.edad
        println("Por favor ingrese su estatura")
        this.estatura
    }
}

    fun main(){

        val person1: Persona = Persona(1.65, true,20, "Liz")
        person1.pedir_datos()
        person1.edad = 10
    }
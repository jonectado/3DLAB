package IntroduccionS

class Calculadora {

}

fun suma(primer_numero: Double,  segundo_numero:Double){
    var suma = primer_numero + segundo_numero
    println("La suma es: $suma")

}

fun resta(primer_numero: Double,  segundo_numero:Double) {
    var resta = primer_numero - segundo_numero
    println("La resta es: $resta")

}

fun multiplicacion(primer_numero: Double,  segundo_numero:Double){
    var multiplicacion = primer_numero * segundo_numero
    println("La multiplicación es: $multiplicacion")

}
fun division(primer_numero: Double,  segundo_numero:Double){
    var division = primer_numero / segundo_numero
    println("La división es: $division")
}
fun main(){
    println("ingrese 1 para sumar. 2 para restar, 3 para multiplicar")
    val select = readln()!!.toInt()

    println("Ingrese el primer número: ")
    var n1 = readln()!!.toDouble()

    println("Ingrese el segundo número: ")
    var n2 = readln()!!.toDouble()

    when(select){
        1-> {suma(n1,n2)}
        2-> {resta(n1,n2)}
        3-> {multiplicacion(n1,n2)}
    }
}
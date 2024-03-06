package IntroduccionN
fun Sumar(primer_numero:Double, segundo_numero:Double){

    var suma = primer_numero + segundo_numero
    println("La suma es: $suma")
}
fun Restar(primer_numero:Double, segundo_numero:Double){
    var resta = primer_numero - segundo_numero
    println("La resta es: $resta")
}
fun Multip(primer_numero:Double, segundo_numero:Double){
    var multiplicacion = primer_numero * segundo_numero
    println("La multiplicación es: $multiplicacion")
}

fun main(){

    println("Inserte el primer numero: ")
    val n1 = readln().toDouble()
    println("Inserte el segundo numero: ")
    val n2 = readln().toDouble()
    println("Ingrese el numero de la función que quiere realizar: \n" +
            "1. Sumar \n" +
            "2. Restar \n" +
            "3. Multiplicar \n")
    when (readln().toInt()) {
        1 -> {
            Sumar(n1,n2)
        }
        2 -> {
            Restar(n1,n2)
        }
        3 -> {
            Multip(n1,n2)
        }
    }
}
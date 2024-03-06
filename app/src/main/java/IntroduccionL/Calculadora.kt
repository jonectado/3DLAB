package IntroduccionL

class Calculadora {
}

fun suma(int1: Int, int2: Int): Int{

    var suma_valor = int1 + int2
    return suma_valor
}

fun resta(int1: Int, int2: Int): Int{

    var resta_valor = int1 - int2
    return resta_valor
}

fun multiplicacion(int1: Int, int2: Int): Int{

    var multiplicacion_valor = int1 * int2
    return multiplicacion_valor
}

fun main(){

    println("Que operación le gustaria realizar:" +
            "1. Suma" + "2. Resta" + "3. Multiplicación")
    var eleccion = readLine()!!.toInt()

    println("Ingrese el primer número para la operación: ")
    var n1 = readLine()!!.toInt()

    println("Ingrese el segundo número para la operación: ")
    var n2 = readLine()!!.toInt()

    when(eleccion){
        1 -> println("La suma es " + suma(n1, n2))
        2 -> println("La resta es "+ resta(n1, n2))
        3 -> println("La multiplicacion es "+ multiplicacion(n1, n2))
    }
}
package IntroduccionS.Taller_1

fun main() {

    println("Num notificaciones")
    var notificaciones: Int = readLine()!!.toInt()

    if (notificaciones in 1..99) {
        println("$notificaciones notificaciones")
    } else if (notificaciones > 99) {
        println("+99 notificaciones")
    } else if (notificaciones == 0) {
        println("No existen mensajes disponibles")
    }
}
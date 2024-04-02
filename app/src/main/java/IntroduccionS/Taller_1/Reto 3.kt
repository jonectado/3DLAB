package IntroduccionS.Taller_1

fun main() {

    val articulos = arrayOf("Cuadro", "Casa en Chia", "Kia Cerato Forte", "Iphone")


    for(articulo in articulos) {

        println("Subasta para: $articulo")
        val diccionario = mutableMapOf<String, Int>()
        println("Ingrese el numero de participantes:")
        var ofertantes: Int = readLine()!!.toInt()
        println("Ingrese la oferta inicial de la casa:")
        var oferta_inicial: Int = readLine()!!.toInt()


        for (i in 1..ofertantes) {
            println("Ingrese su nombre: ")
            val ofertante: String = readLine()!!.toString()

            println("Ingrese su oferta: ")
            var oferta: Int = readLine()!!.toInt()
            // Ingresa al diccionario
            diccionario[ofertante] = oferta

        }
        var max: Int = oferta_inicial
        var ganador: String = "Casa"
        for ((ofertante, oferta) in diccionario) {
            println("Ofertante: $ofertante - Oferta: $oferta")
            if (oferta > max) {
                max = oferta
                ganador = ofertante
            }
        }

        println("La oferta ganadora es de $max para $ganador")
    }
}
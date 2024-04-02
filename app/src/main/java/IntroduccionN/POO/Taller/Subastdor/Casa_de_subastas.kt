fun main() {
    val articulos = listOf(
        "Teléfono inteligente" to 200,
        "Portátil" to 500,
        "Televisor 4K" to 700,
        "Cámara DSLR" to 400,
        "Bicicleta de montaña" to 300
    )
    var a:Int = 1
    var numero_articulo:Int
    var cantidad: MutableList<Int> = mutableListOf(0, 0, 0)
    var ganador:Int = 0
    var precio_alto:Int = 0
    var apostado:Int = 0
    var lb:Int
    var ub:Int
    var hay_ofertas:Int
    while(a<=5){
        precio_alto=0
        numero_articulo = (0..4).random()
        println("El articulo a ofertar será el siguiente: ${articulos[numero_articulo].component1()}\n")
        println("El precio de entrada es: ${articulos[numero_articulo].component2()}\n")
        hay_ofertas =(-1..2).random()
        if(hay_ofertas>=0){
            for(i:Int in 0..hay_ofertas){
                lb = (articulos[numero_articulo].component2())
                ub = (articulos[numero_articulo].component2()+100)
                apostado = (lb..ub).random()
                println("El asistente ${i+1} ofrece $apostado")
                cantidad[i]=apostado
            }
            for(i:Int in 0..hay_ofertas){
                if(cantidad[i]>precio_alto){
                    precio_alto=cantidad[i]
                    ganador=i
                }
            }
            println("\nGana el asistente ${ganador+1}!\n")
        }
        else{
            println("No hay ofertas!")
            println("Gana la casa!\n")
        }
        a+=1
    }
}
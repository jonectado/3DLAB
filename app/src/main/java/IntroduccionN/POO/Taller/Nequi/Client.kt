package IntroduccionN.POO.Taller.Nequi

class Client(usuario: String, contrasena: Int, sald: Double) {
    private var user:String = usuario
    private var password:Int = contrasena
    private var saldo:Double = sald
    private var confirmation = false
    fun login(){
        println("Ingrese su usuario: ")
        val usr = readln().toString()
        println("Ingrese su contraseña: ")
        val pswrd = readln().toInt()
        if (user == usr && password == pswrd) {
            confirmation = true
            loop()
        }else{
            for(i in 3 downTo 1){
                println("Ups! tienes $i intentos más")
                println("Ingrese su usuario: ")
                val usr = readln().toString()
                println("Ingrese su contraseña: ")
                val pswrd = readln().toInt()
                if (user == usr && password == pswrd) {
                    confirmation = true
                    loop()
                }
            }
            println("Lo siento, no puedes entrar :)")
        }
    }
    fun loop(){
        while(confirmation){
            println("¡Bienvenid@!\n" +
                    "Saldo disponible: $saldo\n" +
                    "¿Que desea hacer hoy?\n" +
                    "Saca\n" +
                    "Envia\n" +
                    "Recarga\n" +
                    "Salir\n" +
                    "Escriba el comando: ")
            var command = readln().toString().uppercase()
            when(command){
                "SACA" ->{
                    if(saldo<2000){
                        println("Ups! no te alcanza para hacer esto")
                    }else{
                        println("Escoge de donde sacar: \n" +
                                "Cajero\n" +
                                "Punto físico\n")
                        var punto = readln()
                        println("¿Cuánto desea retirar?")
                        var monto = readln().toDouble()
                        if(monto > saldo){
                            println("Ups! no te alcanza para hacer esto")
                        }else {
                            println("Su codigo para el retiro es: ${(100000..999999).random()}")
                            saldo -= monto
                        }
                    }
                }
                "ENVIA" -> {
                    println("Escriba el número de teléfono al que desea enviar el dinero: ")
                    var num = readln()
                    println("Escriba el valor a enviar: ")
                    var value = readln().toDouble()
                    if(value > saldo){
                        println("Ups! no te alcanza para hacer esto")
                    }else {
                        println("Su transacción fue realizada exitosamente")
                        saldo -= value
                    }
                }
                "RECARGA" -> {
                    println("Escriba el valor a recargar: ")
                    var value = readln().toDouble()
                    println("Desea recargarle $value a su cuenta? (si o no)")
                    var confirm = readln().toString()
                    if(confirm.uppercase()=="SI"){
                        println("Transacción exitosa")
                        saldo += value
                    }else{
                        println("Transacción rechazada")
                    }
                }
                "SALIR" ->{
                    println("Nos vemos en otro momento!")
                    confirmation = false
                }
            }
        }
    }

}

fun main() {
    val nequi = Client("YoMismo", 1234, 4500.0)
    nequi.login()
}
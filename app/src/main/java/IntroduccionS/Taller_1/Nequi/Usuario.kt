package IntroduccionS.Taller_1.Nequi

import java.util.Locale
import kotlin.random.Random

class Nequi(
    var numero: String,
    var contrasena: String,
    var saldo: Int = 4500,
    var sesion: Boolean = false

) {
    init {
        require(numero.all { it.isDigit() }) { "El número de usuario debe ser numérico" }
        require(contrasena.all { it.isDigit() }) { "La contraseña debe ser numérica" }
        require(contrasena.length == 4) { "La contraseña debe tener exactamente 4 dígitos" }
        require(numero.length == 10) { "El numero debe tener exactamente 10 dígitos" }
    }
    fun get_numero(): String{
        return numero
    }
    fun get_saldo(): Int{
        return saldo
    }

    fun cambiarContraseña(){
        println("Ingrese la contraseña actual:")
        var cver : String = readln()
        if(cver == contrasena){
            println("Ingrese la contraseña nueva:")
            contrasena = cver
        }
    }

    fun iniciarsesion(){
        var numero_input = readln()!!
        var contrasena_input = readln()!!
        var contador = 0
        while(contador<4){
            if(numero_input == numero && contrasena==contrasena_input ){
                sesion = true
                contador =5
                println("Saldo: $saldo")
            }else{
                contador +=1
                println("¡Upps! Parece que tus datos de acceso no son correctos")
            }
        }
        if(contador==4){
            println("Ha agotado sus intentos, vuelva más tarde")
        }
    }

    fun generarCodigo(): Int {
        val codigo = (100_000..999_999).random()
        return codigo
    }

    fun saca(){
        println("En donde desea retirar:")
        println("Pulse 1 para retirar en cajero")
        println("Pulse 2 para retirar en punto físico")
        var punto_retiro: Int = readln()!!.toInt()

        if(punto_retiro==1){
            println("Retiro en cajero:")
        }else if(punto_retiro == 2){
            println("Retiro en punto físico:")
        }else{
            println("Opción invalida")
            return
        }

        if(saldo<2000){
            println("No te alcanza")
            return
        }else{
            println("Codigo de retiro: ${generarCodigo()}")
        }
    }

    fun envia(){
        println("Ingrese el numero de destino")
        var numdestino: String = readln()!!

        if(numdestino.length !=10){
            println("Numero invalido")
            return
        }

        println("Ingrese el valor a enviar:")
        var valor: Int = readln()!!.toInt()
        if(valor<saldo){
            println("Saldo insuficiente")
            return
        }else{
            saldo -= valor
        }

    }
    fun recarga(){
        println("Ingrese el valor a recargar")
        var val_recarga: Int = readln()!!.toInt()
        println("¿Desea recargar $val_recarga ?")
        var confirmacion: String = readln()!!.toUpperCase(Locale.ROOT)

        if(confirmacion=="SI"){
            saldo += val_recarga
            println("Su nuevo saldo es: $saldo")
        }else if(confirmacion=="NO"){
            println("Recarga cancelada")
        }else{
            println("Respuesta invalida")
        }
    }
    fun salir(){
        sesion = false
        println("Sesion cerrada")
    }


}
package IntroduccionN.POO.Taller.Notificador

fun main() {
        val num_msg:Int = (0..110).random()
        if(num_msg==0){
            println("No tiene mensajes disponibles")
        }else if(num_msg<100){
            println("Tiene $num_msg notificaciones disponibles.")
        }else{
            println("Tiene +99 notificaciones disponibles.")
        }
}
package IntroduccionL

class Funciones {
}

fun Hello(){
    println("Hello, Liz! :D")
}

fun printHello(name:String?): Unit{

    println("Hi There")
}

fun printHello1(name:String?){

    println("Hi There " + name)
}

fun main(){

    Hello()
    printHello("Liz")
    printHello1("Yuly")
}
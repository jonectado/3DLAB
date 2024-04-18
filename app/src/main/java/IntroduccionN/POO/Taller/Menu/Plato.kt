package IntroduccionN.POO.Taller.Menu

class Plato {
    private var nombre:String
    private var descripcion:String
    private var precio:Int
    private var codigo:Int
    constructor(nombre1:String, descripcion1:String, precio1:Int, codigo1:Int){
        nombre=nombre1
        descripcion=descripcion1
        precio=precio1
        codigo=codigo1
    }
    fun print(){
        println("$nombre: $$precio\n" +
                    descripcion)
    }
    fun get_code():Int{
        return codigo
    }
    fun set_code(code:Int){
        codigo = code
    }
}
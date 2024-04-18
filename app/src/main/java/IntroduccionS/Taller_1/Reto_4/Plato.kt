package IntroduccionS.Taller_1.Reto_4

class Plato {

    private var codigo: Int = 0
    private var nombre: String = ""
    private var descripcion: String = ""
    private var precio: Double = 0.0

    constructor()
    constructor(
        codigo: Int,
        nombre: String,
        descripcion: String,
        precio: Double
    ) : this() {
        this.codigo = codigo
        this.nombre = nombre
        this.descripcion = descripcion
        this.precio = precio
    }
    // Métodos get
    fun getCodigo(): Int {
        return codigo
    }
    fun getNombre(): String {
        return nombre
    }

    fun getDescripcion(): String {
        return descripcion
    }

    fun getPrecio(): Double {
        return precio
    }

    // Métodos set
    fun setCodigo(value: Int) {
        codigo = value
    }
    fun setNombre(value: String) {
        nombre = value
    }

    fun setDescripcion(value: String) {
        descripcion = value
    }

    fun setPrecio(value: Double) {
        precio = value
    }

    //Metodos
    fun platoinfo(){
        println("Codigo: $codigo Nombre: $nombre Descripción: $descripcion Precio: $precio")
    }

}
package IntroduccionS.Taller_1.Reto_4

import IntroduccionS.Taller_1.Reto_4.Plato

class Menu {
    private var entradas: MutableList<Plato> = mutableListOf()
    private var platos_fuertes: MutableList<Plato> = mutableListOf()
    private var postres: MutableList<Plato> = mutableListOf()
    private var bebidas: MutableList<Plato> = mutableListOf()

    constructor()
    constructor(
        entradas: MutableList<Plato>,
        platos_fuertes: MutableList<Plato>,
        postres: MutableList<Plato>,
        bebidas: MutableList<Plato>
    ) : this() {
        this.entradas = entradas
        this.platos_fuertes = platos_fuertes
        this.postres = postres
        this.bebidas = bebidas
    }
    //Getters
    fun get_entradas():MutableList<Plato>{
        return this.entradas
    }
    fun get_platos_fuertes():MutableList<Plato>{
        return this.platos_fuertes
    }
    fun get_postres():MutableList<Plato>{
        return this.postres
    }
    fun get_bebidas():MutableList<Plato>{
        return this.bebidas
    }
    //Setters
    fun set_entradas(value:MutableList<Plato>){
        entradas = value
    }
    fun set_platos_fuertes(value:MutableList<Plato>){
        platos_fuertes = value
    }
    fun set_postres(value:MutableList<Plato>){
        postres = value
    }
    fun set_bebidas(value:MutableList<Plato>){
        bebidas = value
    }

    //Metodos
    fun anadir_entrada(codigo: Int, nombre: String, descripcion: String, precio: Double) {
        val entrada = Plato(codigo, nombre, descripcion, precio)
        entradas.add(entrada)
    }

    fun anadir_plato_fuerte(codigo: Int, nombre: String, descripcion: String, precio: Double) {
        val platoFuerte = Plato(codigo, nombre, descripcion, precio)
        platos_fuertes.add(platoFuerte)
    }

    fun anadir_postre(codigo: Int, nombre: String, descripcion: String, precio: Double) {
        val postre = Plato(codigo, nombre, descripcion, precio)
        postres.add(postre)
    }

    fun anadir_bebida(codigo: Int, nombre: String, descripcion: String, precio: Double) {
        val bebida = Plato(codigo, nombre, descripcion, precio)
        bebidas.add(bebida)
    }

    fun eliminar_entrada(codigo:Int){
        for(entrada in entradas){
            if(entrada.getCodigo()==codigo){
                val indice_entrada = entradas.indexOf(entrada)
                entradas.removeAt(indice_entrada)
                break
            }
        }
    }

    fun eliminar_plato_fuerte(codigo: Int) {
        for (platoFuerte in platos_fuertes) {
            if (platoFuerte.getCodigo() == codigo) {
                val indice_platoFuerte = platos_fuertes.indexOf(platoFuerte)
                platos_fuertes.removeAt(indice_platoFuerte)
                break
            }
        }
    }

    fun eliminar_postre(codigo: Int) {
        for (postre in postres) {
            if (postre.getCodigo() == codigo) {
                val indice_postre = postres.indexOf(postre)
                postres.removeAt(indice_postre)
                break
            }
        }
    }

    fun eliminar_bebida(codigo: Int) {
        for (bebida in bebidas) {
            if (bebida.getCodigo() == codigo) {
                val indice_bebida = bebidas.indexOf(bebida)
                bebidas.removeAt(indice_bebida)
                break
            }
        }
    }

    fun ver_menu(){
        println("Entradas:")
        for(entrada in entradas){
            entrada.platoinfo()
        }
        println("Platos fuertes")
        for(pf in platos_fuertes){
            pf.platoinfo()
        }
        println("Postres")
        for(p in postres){
            p.platoinfo()
        }
        println("Bebidas: ")
        for(b in bebidas){
            b.platoinfo()
        }
    }


}

fun main(){
    var menu1:Menu = Menu()

    menu1.anadir_entrada(1,"Deditos","De queso",18000.0)
    menu1.anadir_entrada(2,"empanadas","Carne o queso",10900.5)

    menu1.anadir_postre(1,"Coco","Churus",5000.0)

    menu1.eliminar_entrada(2)
    menu1.ver_menu()
}



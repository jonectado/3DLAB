package IntroduccionN.POO.Taller.Menu

class Carta(
    maincourse: MutableList<Plato>,
    dessert: MutableList<Plato>,
    drinks: MutableList<Plato>,
    entries: MutableList<Plato>
) {
    private var entradas:MutableList<Plato> = entries
    private var principal:MutableList<Plato> = maincourse
    private var bebidas:MutableList<Plato> = drinks
    private var postres:MutableList<Plato> = dessert

    fun print(){
        println("Entradas:\n")
        for(i in entradas){
            i.print()
            println("")
        }
        println("Platos principales:\n")
        for(i in principal){
            i.print()
            println("")
        }
        println("Bebidas:\n")
        for(i in bebidas){
            i.print()
            println("")
        }
        println("Postres:\n")
        for(i in postres){
            i.print()
            println("")
        }
    }
    fun add_main(nuevoplato: Plato){
        principal.add(nuevoplato)
        reorder("principal")
    }
    fun add_dessert(nuevoplato: Plato){
        postres.add(nuevoplato)
        reorder("postres")
    }
    fun add_drink(nuevoplato: Plato){
        bebidas.add(nuevoplato)
        reorder("bebidas")
    }
    fun add_entrance(nuevoplato: Plato) {
        entradas.add(nuevoplato)
        reorder("entradas")
    }

    private fun reorder(lista:String){
        val list = lista
        when (list){
            "entradas" ->{
                var difference = 0
                for (i in entradas){
                    if(i.get_code() - difference > 1){
                        var newcode = i.get_code()
                        while(newcode - difference > 1){
                            newcode--
                        }
                        i.set_code(newcode)
                    }else if(i.get_code() - difference < 1){
                        var newcode = i.get_code()
                        while(newcode - difference < 1){
                            newcode++
                        }
                        i.set_code(newcode)
                    }
                    difference = i.get_code()
                }
            }
            "postres" ->{
                var difference = 0
                for (i in postres){
                    if(i.get_code() - difference > 1){
                        var newcode = i.get_code()
                        while(newcode - difference > 1){
                            newcode--
                        }
                        i.set_code(newcode)
                    }else if(i.get_code() - difference < 1){
                        var newcode = i.get_code()
                        while(newcode - difference < 1){
                            newcode++
                        }
                        i.set_code(newcode)
                    }
                    difference = i.get_code()
                }
            }
            "principal" ->{
                var difference = 0
                for (i in principal){
                    if(i.get_code() - difference > 1){
                        var newcode = i.get_code()
                        while(newcode - difference > 1){
                            newcode--
                        }
                        i.set_code(newcode)
                    }else if(i.get_code() - difference < 1){
                        var newcode = i.get_code()
                        while(newcode - difference < 1){
                            newcode++
                        }
                        i.set_code(newcode)
                    }
                    difference = i.get_code()
                }
            }
            "bebidas" ->{
                var difference = 0
                for (i in bebidas){
                    if(i.get_code() - difference > 1){
                        var newcode = i.get_code()
                        while(newcode - difference > 1){
                            newcode--
                        }
                        i.set_code(newcode)
                    }else if(i.get_code() - difference < 1){
                        var newcode = i.get_code()
                        while(newcode - difference < 1){
                            newcode++
                        }
                        i.set_code(newcode)
                    }
                    difference = i.get_code()
                }
            }
        }
    }

    fun delete(){
        println("Escriba de que parte quiere eliminar:\n" +
                "Entradas\n" +
                "Bebidas\n" +
                "Principales\n" +
                "Postres\n")
        val lista = readln().toString().uppercase()
        when(lista){
            "ENTRADAS" -> for (i in entradas){
                i.print()
                println("¿Desea eliminar este item? (Si o No)")
                val decision = readln().uppercase()
                if(decision =="SI"){
                    entradas.remove(i)
                    reorder("entradas")
                    break
                }else{
                    continue
                }
            }
            "POSTRES" -> for (i in postres){
                i.print()
                println("¿Desea eliminar este item? (Si o No)")
                val decision = readln().uppercase()
                if(decision =="SI"){
                    postres.remove(i)
                    reorder("postres")
                    break
                }else{
                    continue
                }
            }
            "PRINCIPAL" -> for (i in principal){
                i.print()
                println("¿Desea eliminar este item? (Si o No)")
                val decision = readln().uppercase()
                if(decision =="SI"){
                    principal.remove(i)
                    reorder("principal")
                    break
                }else{
                    continue
                }
            }
            "BEBIDAS" -> for (i in bebidas){
                i.print()
                println("¿Desea eliminar este item? (Si o No)")
                val decision = readln().uppercase()
                if(decision =="SI"){
                    bebidas.remove(i)
                    reorder("bebidas")
                    break
                }else{
                    continue
                }
            }
        }
    }
}

fun main() {
    val maincourse = mutableListOf<Plato>()
    val dessert = mutableListOf<Plato>()
    val drinks = mutableListOf<Plato>()
    val entries = mutableListOf<Plato>()

    // Platos
    maincourse.add(Plato("Lasaña", "Lasaña de carne con salsa de tomate y queso", 25000, 1))
    maincourse.add(Plato("Sushi", "Rollos de sushi variados con pescado fresco", 30000, 2))
    maincourse.add(Plato("Hamburguesa", "Hamburguesa gourmet con carne de res y queso cheddar", 20000, 3))
    maincourse.add(Plato("Pizza", "Pizza tradicional italiana con pepperoni y champiñones", 22000, 4))
    maincourse.add(Plato("Pasta Alfredo", "Pasta tipo fettuccine con salsa Alfredo cremosa", 18000, 5))
    maincourse.add(Plato("Tacos", "Tacos mexicanos con carne de cerdo marinada", 18000, 6))
    maincourse.add(Plato("Arroz con Pollo", "Arroz con pollo al estilo latino con vegetales mixtos", 20000, 7))
    maincourse.add(Plato("Parrillada", "Selección de carnes a la parrilla con guarniciones", 35000, 8))

    // Postres
    dessert.add(Plato("Tiramisú", "Postre italiano de café con capas de bizcocho y crema mascarpone", 10000, 1))
    dessert.add(Plato("Cheesecake", "Pastel de queso horneado con salsa de frutos rojos", 12000, 2))

    // Bebidas
    drinks.add(Plato("Mojito", "Cóctel refrescante de ron, menta, lima y soda", 15000, 1))
    drinks.add(Plato("Margarita", "Cóctel clásico de tequila con jugo de lima y triple sec", 12000, 2))
    drinks.add(Plato("Piña Colada", "Cóctel tropical de ron, piña y crema de coco", 14000, 3))

    //Entradas
    entries.add(Plato("Ensalada César", "Ensalada fresca con pollo a la parrilla y aderezo César", 15000, 1))
    entries.add(Plato("Sopa de Tomate", "Sopa caliente de tomate con albahaca fresca", 12000, 2))

    val carta: Carta = Carta(maincourse,dessert,drinks,entries)
    carta.print()
    carta.add_dessert(Plato("Pepe","Mi compa pepe, el mejor", 2, 1))
    carta.print()

}


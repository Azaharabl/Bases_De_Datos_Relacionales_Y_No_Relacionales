
import controllers.PersonasController
import controllers.VehiculoController
import controllers.manager.ManagerControler
import dbManager.DBManager
import entyties.Persona
import entyties.Vehiculo

fun main(args: Array<String>) {
    println("Hello Mongo BBDD World!")

    println("\n \n Ejecutamos el doker con los comandos que he dejado en el readme.md")

    DBManager.crearColecion("personas")
    var personas = PersonasController()
    crudConPersonas(personas)


    DBManager.mostrarDatabases()
    DBManager.mostrarColeciones()

    println("\n\n Ahora realizaremos las mismas operaciones con vehiculo")

    DBManager.crearColecion("vehiculos")
    var vehiculos = VehiculoController()

    ejecutamosCrudVehiculos(vehiculos)


    DBManager.mostrarDatabases()
    DBManager.mostrarColeciones()
    println("\n \n Comprobamos ahora las relaciones que hemos creaso,\n" +
            " hay que tener en cuanta que Mongo no funciona con relaciones\n " +
            "por lo que nosotros tenemos que manejarlas a traves de código")

    relacionesUnoAMuchos(vehiculos, personas)


}


fun relacionesUnoAMuchos(vehiculos: VehiculoController, personas: PersonasController) {
   println("en este caso una persona tiene varios coches, " +
           "pero si la persona se borra nosotros buscamos todos los coches y ponemos ese id a null." +
           "\n tambien decidimos solo endever la referencia y no el objeto ")

    val manager = ManagerControler(personas, vehiculos )
    println("creamos un vehiculo y dos personas")
    var v = Vehiculo(mark = "coche de alquiler")
    var mohamet = Persona(nombre = " Mohamet")
    var rocio = Persona(nombre = "Rocio")

    manager.createPersona(rocio)
    manager.createPersona(mohamet)
    manager.createVehiculo(v)

    println("imprimimos todo")
    println(manager.findAllPersonas())
    println(manager.findAllVehiculos())

    println("al vehiculo lo alquilan")
    v.personaId = mohamet.uuid
    manager.updateVehiculo(v)

    println("imprimimos todo")
    println(manager.findAllPersonas())
    println(manager.findAllVehiculos())

    println("a ese mismo vehiculo lo alquila otra persona")

    v.personaId =  rocio.uuid
    manager.updateVehiculo(v)

    println("imprimimos todo")
    println(manager.findAllPersonas())
    println(manager.findAllVehiculos())


    println("intentamos borra la persona que tiene ese vehiculo")
    manager.deletePersona(rocio.uuid)
    println("imprimimos todo")
    println(manager.findAllPersonas())
    println(manager.findAllVehiculos())

    println("intentamos ponerle una persona que no exixte en la bbdd")
    v.personaId =  "inventadisimo"
    manager.updateVehiculo(v)
    println("imprimimos todo")
    println(manager.findAllPersonas())
    println(manager.findAllVehiculos())


}

private fun ejecutamosCrudVehiculos(vehiculos: VehiculoController) {
    println("Buscamos todos los vehiculos")
    println(vehiculos.findAll())

    println("Creamos un par de vehiculos")
    var vehiculo1 = Vehiculo(mark = "vehiculo de Dani")
    var vehiculo2 = Vehiculo(mark = "vehiculo de Azahara ")
    vehiculos.create(vehiculo1)
    vehiculos.create(vehiculo2)


    println("Buscamos todos los vehiculos")
    println(vehiculos.findAll())

    println("Buscamos vehiculo por uuid")
    var vehiculoEncontrado = vehiculos.findByUuid(vehiculo1.id)
    println(vehiculoEncontrado)

    println("Borramos un vehiculo de la bbdd")
    vehiculos.delete(vehiculo1.id)

    println("Buscamos el vehiculo borrado por uuid")

    var vehiculoEncontrado2 =vehiculos.findByUuid(vehiculo1.id)
    if (vehiculoEncontrado2 == null) {
        println(" ha funcionado. el vehiculo no existe")
    } else {
        println(vehiculoEncontrado2)
    }

    println("Buscamos todos los vehiculos")
    println(vehiculos.findAll())

    println("modificamos el vehiculo")
    vehiculo2.mark = " coche modificado"
    println(vehiculo2)
    vehiculos.update(vehiculo2)

    println("Buscamos todos los vehiculos")
    println(vehiculos.findAll())


    DBManager.mostrarDatabases()
    DBManager.mostrarColeciones()
}

private fun crudConPersonas(personas: PersonasController) {
    println("borramos la bbdd por si tiene antiguos datos, si quieres puedes comentarla para ver datos antiguos")
    personas.deleteAll()

    println("otra forma es borrando la bbdd")
    DBManager.reiniciarbbdd()

    println("Buscamos todas las personas")
    println(personas.findAll())

    println("Creamos un par de personas")
    var azahara = Persona(nombre = "Azahara")
    var daniel = Persona(nombre = "Daniel")
    personas.create(azahara)
    personas.create(daniel)


    println("Buscamos todas las personas")
    println(personas.findAll())

    println("Buscamos persona por uuid")
    var personaEncontada = personas.findByUuid(azahara.uuid)
    println(personaEncontada)

    println("Borramos una persona de la bbdd")
    personas.delete(azahara.uuid)

    println("Buscamos la persona borrada persona por uuid")

    var personaEncontada2 = personas.findByUuid(azahara.uuid)
    if (personaEncontada2 == null) {
        println("la persona no existe")
    } else {
        println(personaEncontada2)
    }

    println("Buscamos todas las personas")
    println(personas.findAll())

    println("modificamos a la persona daniel para ponerle una edad distinta")
    daniel.edad = 22
    personas.update(daniel)

    println("Buscamos todas las personas")
    println(personas.findAll())
}



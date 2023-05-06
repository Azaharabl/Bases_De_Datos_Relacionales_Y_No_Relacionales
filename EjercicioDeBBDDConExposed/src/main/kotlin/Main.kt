
import database.DataBaseManager
import poko.Persona
import poko.Vehiculo
import repository.PersonasRepository
import repository.VehiculoRepository

fun main(args: Array<String>) {
    println("Hello Exposed World!")


    DataBaseManager.init()
    var personaRepository = PersonasRepository()
    var vehiculoRepository = VehiculoRepository()



    println("Creamos y añadimos Personas ")
    var azahara: Persona? = Persona(nombre = "Azahara")
    println(azahara)
    var daniel: Persona? = Persona(nombre = "Daniel")
    println(daniel)

    azahara = azahara?.let { personaRepository.create(it) }
    daniel = daniel?.let { personaRepository.create(it) }

    println(azahara)
    println(daniel)

    println("Buscamos persona por id")
    if (azahara != null) {
        println(personaRepository.findById(azahara.personaId))
    }

    println("Leemos todas las personas")
    println(personaRepository.findAll())

    println("Modificamos persona")
    if (daniel != null) {
        daniel.nombre = "Nombre Modificado"
        println("modificamoc a : " + daniel)
        personaRepository.update(daniel)
    }

    println("Buscamos persona por id")
    if (daniel != null) {
        println(personaRepository.findById(daniel.personaId))
    }

    println("Leemos todas las personas")
    println(personaRepository.findAll())

    println("Borramos persona")
    if (daniel != null) {
        personaRepository.deletteById(daniel.personaId)
    }

    println("Leemos todas las personas")
    println(personaRepository.findAll())


    /// Vehiculos

    println("Creamos y añadimos Vehiculos ")
    var v: Vehiculo? = Vehiculo(mark = "ford", personaId = null)
    println(v)
    var v2: Vehiculo? = Vehiculo(mark = "rexton", personaId = null)
    println(v2)


    v = v?.let { vehiculoRepository.create(v!!) }
    v2 = v2?.let { vehiculoRepository.create(v2!!) }

    println(v)
    println(v2)

    println("Buscamos vehiculo por uuid")
    if (v != null) {
        println(vehiculoRepository.findById(v.uuid))
    }

    println("Leemos todos los vehiculos")
    println(vehiculoRepository.findAll())

    println("Modificamos vehiculo")
    if (v2 != null) {
        v2.mark = "Marca Modificado"
        println("modificamoc a : " + v2)
        vehiculoRepository.update(v2)
    }

    println("Buscamos vehiculo por id")
    if (v2 != null) {
        println(vehiculoRepository.findById(v2.uuid))
    }

    println("Leemos todos los vehiculos")
    println(vehiculoRepository.findAll())

    println("Borramos vehiculo")
    if (v2 != null) {
        vehiculoRepository.deletteById(v2.uuid)
    }

    println("Leemos todos los vehiculos")
    println(vehiculoRepository.findAll())


    //references
    println("\n \n creamos una persona nueva y un vehiculo nuevo el que añadiremos el id de la persona")


    var maria: Persona? = Persona(nombre = "Maria")       //creamos

    if (maria != null) {


        maria = personaRepository.create(maria)
    }    //añadimos a la bbdd si no daria error

    println(personaRepository.findAll())

    var v3: Vehiculo? = Vehiculo(mark = "vehiculo3", personaId = maria?.personaId)    //creamos vehiculo con persona id

    println("vehiculo a crear: " + v3)

    if (v3 != null) {
        vehiculoRepository.create(v3)
    }    //añadimos a la bbdd el vehiculo

    println("buscamos el vehiculo y lo emprimimos para ver que esta bien guardado")
    println(vehiculoRepository.findAll())

    println("ahora borraremos la persona de la bbdd y al buscar de nuevo el vehiculo tendría que salir el PersonaId a nulo")
    if (maria != null) {
        personaRepository.deletteById(maria.personaId)
    } //borramos persona
    println(vehiculoRepository.findAll())       //vemos el resultado


    println(
        "\n \n vamos a comprobar que . unikeIndex funciona, haciendo la relacion 1-1 o 1-m , \n " +
                "para esto en la tabl" +
                "a Vehiculos puedes comendar o descomentar la linea de unike index"
    )



    var rocio : Persona? = Persona(nombre = "Rocio")
    rocio = rocio?.let { personaRepository.create(it) }         //metemos en BBDD de nuevo a rocio en la bbdd

    if (rocio != null) {
         var v4 = Vehiculo(mark = "vehiculo de Rocio 1", personaId = rocio?.personaId)    //creamos vehiculo con persona id
        vehiculoRepository.create(v4)                                                     //metemos en bbdd el vehiculo

        println("imprimimos todos los vehiculos para ver el resultado")
        println(vehiculoRepository.findAll())

        try {
            var v5 = Vehiculo(mark = "vehiculo de Rocio 2", personaId = rocio?.personaId)   //creamos otro coche
            vehiculoRepository.create(v5)                                                   // metemos en la bbdd
            println("Como la linea .unikeIndex No! está activa Rocio Si! puede tener más un coche, y se te va a guardar en la bbdd")
            println("ahora sacaremos todos los coches de rocio")

        } catch (e: Exception) {
            println("Como la linea .unikeIndex está activa Rocio solo puede tener un coche, no se te va a guardar en la bbdd")
        }
        println("imprimimos todos los vehiculos para ver el resultado")
        println(vehiculoRepository.findAll())
        println("coches que pertenecen a rocio: ")
        println(vehiculoRepository.findByPersonaId(rocio.personaId))


    }
}


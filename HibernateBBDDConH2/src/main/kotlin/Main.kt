import entity.Persona
import entity.Vehiculo
import hibernate.HivernateManager
import repository.PersonaRepository
import repository.VehiculoRepository
import java.util.UUID

fun main(args: Array<String>) {
    println("Hello World!")

    initDatabase()

   // var vehiculos = VehiculoRepository()
    var personaRepository = PersonaRepository()
    var vehiculosRepository = VehiculoRepository()


    println("Creamos y añadimos Personas ")
    var p = Persona(nombre = "Azahara" )
    println(p)
    var p2 = Persona(nombre = "Daniel" )
    println(p2)

     p = personaRepository.create(p)
     p2 = personaRepository.create(p2)

    println(p )
    println(p2 )

    println("Buscamos persona por id")
    println(personaRepository.getById(1))

    println("Leemos todas las personas")
    println(personaRepository.getAll())

    println("Modificamos persona")
    p2.nombre = "Nombre Modificado"
    personaRepository.update(p2)

    println("Leemos todas las personas")
    println(personaRepository.getAll())

    println("Borramos persona")
    personaRepository.delete(p2)

    println("Leemos todas las personas")
    println(personaRepository.getAll())


    println("Creamos y añadimos Vehiculo ")
    var v = Vehiculo(UUID.randomUUID().toString(), "coche  pequeño", "modelo", engine ="motor")
    println(v)
    var v2 = Vehiculo(UUID.randomUUID().toString(), "coche Garnde", "modelo2", engine ="motor2")
    println(v2)

    v = vehiculosRepository.create(v)
    v2 = vehiculosRepository.create(v2)

    println(v )
    println(v2 )

    println("Buscamos vehiculo por id")
    println(vehiculosRepository.getById(v.uuid))

    println("Leemos todas los vehiculo")
    println(vehiculosRepository.getAll())

    println("Modificamos vehiculo")
    v2.model = "Modelo Modificado"
    vehiculosRepository.update(v2)

    println("Leemos todos los vehiculo")
    println(vehiculosRepository.getAll())

    println("Borramos vehiculo")
    vehiculosRepository.delete(v)

    println("Leemos todas las vehiculo")
    println(vehiculosRepository.getAll())


}

fun initDatabase() {
    HivernateManager.open()
    HivernateManager.close()
}


package controllers.manager

import controllers.PersonasInterface
import controllers.VehiculosInterface
import entyties.Persona
import entyties.Vehiculo

class ManagerControler (
    val personas : PersonasInterface,
    val vehiculos : VehiculosInterface
){
     fun createPersona(p: Persona) {
         println("Creamos persona")
        personas.create(p)
    }

     fun createVehiculo(p: Vehiculo) {
         println("Creamos Vehiculo:")
         if (p.personaId != null){  //si es nulo no comprobamos
             println("comprobamos que el id de la persona exixte")
            var personaCorrecta = personas.findByUuid(p.personaId!!)
             if (personaCorrecta == null){
                 println("el vehiculo no ha posiso ser creado: \n " +
                         "la persona a la que pertenece no exite en el registro")
             }else{
                 vehiculos.create(p)
                 println("vehiculo creado : persona correcta")
             }
         }else{
             vehiculos.create(p)
             println("vehiculo creado : persona null")
         }

    }

    fun findByUuidVehiculo(uuid: String): Vehiculo? {
        println("findByUuidVehiculo")
        return vehiculos.findByUuid(uuid)
    }
    fun findByUuidPersona(uuid: String): Persona? {
        println("findByUuidPersona")
        return personas.findByUuid(uuid)
    }

    fun updateVehiculo(p: Vehiculo) {
        println("updateVehiculo")
        println("para cambiar el vehiculo tenemos que comprobar que la persona exite")
        if (p.personaId != null){  //si es nulo no comprobamos
            println("comprobamos que el id de la persona exixte porque no es null")
            var personaCorrecta = personas.findByUuid(p.personaId!!)
            if (personaCorrecta == null){
                println("el vehiculo no ha podido ser actualizado: \n " +
                        "la persona a la que pertenece no exite en el registro")
            }else{
                vehiculos.update(p)
                println("vehiculo actualizado : persona correcta")
            }
        }else{
            vehiculos.update(p)
            println("vehiculo Actualizado: persona null")
        }

    }
    fun updatePersona(p: Persona) {
        println("updatePersona")
        //aqui hay varias opciones dependiendo de si queremos update en cascada o no
        //print en mi caso si vamos a hacerlo, por lo que no tenemos que cambiar nada
        personas.update(p)


        /**
        //en el caso que queramos no actualizarlos, hariamos lo siguiente

        var listaVehiculos = vehiculos.findAllByPersonaId(p.uuid)
        if(listaVehiculos.size ==0){
        println("se puede actualizar ya que no hay vehiculos a su nombre")
        personas.update(p)
        }else{
        println("No se puede actualizar ya que no hay vehiculos a su nombre")
        }

         */
    }

    fun deletePersona(uuid: String) {
        println("delete persona")
        //aqui decidimos si la idPersona es delete on cascade = true y null o no
        // en mi caso si hay coches con esa persona , esta no se puede borrar

        var listaVehiculos = vehiculos.findAllByPersonaId(uuid)
        if(listaVehiculos.size ==0){
            println("se puede Borrar ya que no hay vehiculos a su nombre")
            personas.delete(uuid)
        }else{
            println("No se puede Borrar ya que hay vehiculos a su nombre")
        }

    }
    fun deleteVehiculo(uuid: String) {
        println( "deletingVehiculo")
        //aqui decidimos si las personas se borran al borar el vehiculo
        //no me parece que tenga sentido en este programa
        vehiculos.delete(uuid)
    }

    fun findAllVehiculos(): List<Vehiculo> {
        println("findAllVehiculos")
       return vehiculos.findAll()
    }
    fun findAllPersonas(): List<Persona> {
        println("findAllPersonas")
        return personas.findAll()
    }

    fun deleteAllVehiculos() {
        println("deleteAllVehic")
       vehiculos.deleteAll()
    }
    fun deleteAllPersonas() {
        println("deleteAllPersonas")
        personas.deleteAll()
    }



}
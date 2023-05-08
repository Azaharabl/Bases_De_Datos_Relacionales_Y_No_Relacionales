package controllers

import dbManager.DBManager
import entyties.Persona
import entyties.Vehiculo
import maper.ToDocument
import maper.toPersona
import maper.toVehiculo
import org.bson.Document
import java.time.LocalDate

class VehiculoController(): VehiculosInterface {

    //al añadir la persona tenemso que comprobar que la persona en la bbdd personas está

    private val collection = DBManager.database.getCollection("vehiculos")


    override fun findAllByPersonaId(personaId: String): List<Vehiculo> {
        var query = Document("persona_id", personaId)
        var lista = ArrayList<Vehiculo>()
        var result = collection.find(query).forEach{
            var v = it.toVehiculo()
            if (v != null) {lista.add(v)}
        }
        return lista
    }


    override fun create(p: Vehiculo) {
      collection.insertOne(Document("id", p.id)
          .append("mark", p.mark)
          .append("model", p.model)
          .append("dateVenta", p.dateVenta)
          .append("engine", p.engine)
          .append("createAt", p.createAt)
          .append("deleted", p.deleted)
          .append("persona_id", p.personaId))
    }

    override fun findByUuid(uuid: String): Vehiculo? {

        var  personaEncontrada: Vehiculo? = null
        try {
            val personaEncontrada  = collection.find(Document("id", uuid)).first().toVehiculo()
            return personaEncontrada
        }catch (e: Exception) {
            println("persona no encontrada")
        }
        return null

    }

    override fun update(p: Vehiculo) {
       collection.replaceOne(Document("id", p.id), p.ToDocument())
    }

    override fun delete(uuid: String) {
       collection.deleteOne(Document("id", uuid))
    }

    override fun findAll(): List<Vehiculo> {
        var result = ArrayList<Vehiculo>()
        collection.find().forEach {
           var v = it.toVehiculo()
           if (v != null) {result.add(v)}
       }
        return result
    }

    override fun deleteAll() {
        return collection.find().forEach { delete(it.getString("id")) }
    }


}
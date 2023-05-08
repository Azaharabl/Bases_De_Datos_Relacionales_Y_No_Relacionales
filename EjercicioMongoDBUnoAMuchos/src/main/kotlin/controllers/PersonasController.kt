package controllers

import dbManager.DBManager
import entyties.Persona
import maper.toDocument
import maper.toPersona
import org.bson.Document


class PersonasController(): PersonasInterface {


    private val collection = DBManager.database.getCollection("personas")

    override fun create(p : Persona) {
        collection.insertOne(p.toDocument());
    }

    override fun findByUuid(uuid: String): Persona? {
        val query = Document("uuid", uuid)
        var  personaEncontrada: Persona? = null
        try {
            val result = collection.find(query).first()
             personaEncontrada = result.toPersona()
        }catch (e: Exception) {
            println("persona no encontrada")
        }
        if (personaEncontrada != null) {
            return personaEncontrada
        }else{
            return null
        }
    }

    override fun update(p: Persona) {
        val query = Document("uuid", p.uuid)
        collection.replaceOne(query, p.toDocument())
    }

    override fun delete(uuid: String) {
        val query = Document("uuid", uuid)
        collection.deleteOne(query)
    }

    override fun findAll(): List<Persona> {
        val listaPersonas = mutableListOf<Persona>()
        for (persona in collection.find()) {
            val p = persona.toPersona()
            if (p != null) {
                listaPersonas.add(p)
            }
        }
        return listaPersonas
    }

    override fun deleteAll(){
        for (persona in collection.find()) {
           collection.deleteOne(Document("uuid", persona.getString("uuid")))
        }
    }

}
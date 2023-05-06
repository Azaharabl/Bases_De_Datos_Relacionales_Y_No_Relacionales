package repository

import entitys.PersonaDao
import entitys.PersonasTable
import mapers.toPersona
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import poko.Persona

class PersonasRepository (


)  : CrudRepository<Persona, Long>{

    val personas = PersonaDao


    override fun findAll(): List<Persona> {
        var p : List<Persona> = listOf()
        transaction {
            p = personas.all().map{ it.toPersona()}
        }
        return p
    }

    override fun findById(id: Long): Persona? {
       var p : Persona? = null
        transaction {
            p = personas.findById(id)?.toPersona()
        }
        return p
    }

    override fun deletteById(id: Long): Boolean {
        var p : Persona? = null
        transaction {
             p= personas.findById(id)?.toPersona()
        }

        if (p == null){
            return false
        }else{
            transaction {
                personas.findById(id)?.delete()
            }
        }
        return true

    }

    override fun update(t: Persona): Boolean {
        var p : PersonaDao? = null
        var exito = false

        transaction {
            p =  personas.findById(t.personaId)
            p?.apply {
                nombre = t.nombre
                fechaCarnet = t.fechaCarnet
            }
            exito= true
        }
        println(p)
        return exito
    }

    override fun create(t: Persona): Persona? {
        var p : Persona? = null
        transaction {
           p =  personas.new(){
                nombre = t.nombre
                fechaCarnet = t.fechaCarnet
            }.toPersona()

        }
        return p
    }


}


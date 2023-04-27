package repository


import entity.Persona
import hibernate.HivernateManager
import hibernate.HivernateManager.manager
import hibernate.HivernateManager.query
import repository.InterfacesReposiitory.PersonaReposytoryInterface
import javax.persistence.TypedQuery

class PersonaRepository : PersonaReposytoryInterface {



    override fun create(t: Persona): Persona {
        var personaConIdactualizado : Persona = t
        HivernateManager.transaction {
            manager.persist(t)
            personaConIdactualizado = manager.find(Persona::class.java, t.id)
        }
        return personaConIdactualizado
    }

    override fun update(t: Persona): Boolean {
        var res = false
        HivernateManager.transaction {
            manager.merge(t)
            res = true
        }
        return res
    }

    override fun delete(t: Persona): Boolean {
        var res = false
        HivernateManager.transaction {
            val usuario = manager.find(Persona::class.java, t.id)
            usuario?.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }



    override fun getById(id: Long): Persona? {

        var p: Persona? = null
        HivernateManager.transaction {
            p = manager.find(Persona::class.java, id)
        }
        return p

    }

    override fun getAll(): List<Persona> {
        var personas = mutableListOf<Persona>()
        HivernateManager.query {
            val query: TypedQuery<Persona> = manager.createNamedQuery("Persona.findAll", Persona::class.java)
            personas = query.resultList
        }
        return personas
    }


}
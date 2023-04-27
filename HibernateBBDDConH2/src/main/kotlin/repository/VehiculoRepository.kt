package repository

import entity.Persona

import entity.Vehiculo
import hibernate.HivernateManager
import hibernate.HivernateManager.manager
import repository.InterfacesReposiitory.VehiculoReposytoryInterface
import javax.persistence.TypedQuery

class VehiculoRepository : VehiculoReposytoryInterface {
    override fun create(t: Vehiculo): Vehiculo {
        var persona = t
        HivernateManager.transaction {
            manager.persist(t)
            persona = manager.find(Vehiculo::class.java, t.uuid)
        }
        return persona
    }

    override fun update(t: Vehiculo): Boolean {
        var res = false
       HivernateManager.transaction {
           manager.merge(t)
           res = true
       }
        return res
    }

    override fun delete(t: Vehiculo): Boolean {
        var res = false

       HivernateManager.transaction {
           val vehiculo = manager.find(Vehiculo::class.java, t.uuid)
           vehiculo.let {
               manager.remove(it)
               res = true
           }
       }
        return res

    }

    override fun getById(id: String): Vehiculo? {
        var res : Vehiculo? = null
        HivernateManager.transaction {
            res = manager.find( Vehiculo::class.java, id)
        }
        return res
    }

    override fun getAll(): List<Vehiculo> {
        var vehiculos = mutableListOf<Vehiculo>()
        HivernateManager.query {
            val query = manager.createNamedQuery("Vehiculo.findAll", Vehiculo::class.java)
             vehiculos = query.resultList
        }
        return vehiculos
    }


}
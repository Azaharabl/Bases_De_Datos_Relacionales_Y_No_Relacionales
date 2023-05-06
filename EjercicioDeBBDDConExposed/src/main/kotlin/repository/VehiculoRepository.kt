package repository

import entitys.PersonaDao
import entitys.VehiculoDao
import entitys.VehiculoTable
import entitys.VehiculoTable.personaId
import mapers.toPersona
import mapers.toVehiculo
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import poko.Persona
import poko.Vehiculo
import java.util.UUID

class VehiculoRepository (


)  : CrudRepository<Vehiculo, UUID> {

    val vehiculos = VehiculoDao
    var personas = PersonaDao


    override fun findAll(): List<Vehiculo> {
        var p: List<Vehiculo> = listOf()
        transaction {
            p = vehiculos.all().map { it.toVehiculo() }
        }
        return p
    }

    override fun findById(uuid: UUID): Vehiculo? {
        var p: Vehiculo? = null
        transaction {
            p = vehiculos.findById(uuid)?.toVehiculo()
        }
        return p
    }

    override fun deletteById(uuid: UUID): Boolean {
        var p: Vehiculo? = null
        transaction {
            p = vehiculos.findById(uuid)?.toVehiculo()
        }

        if (p == null) {
            return false
        } else {
            transaction {
                vehiculos.findById(uuid)?.delete()
            }
        }
        return true

    }

    override fun update(t: Vehiculo): Boolean {
        var p: VehiculoDao? = null
        var exito = false

        transaction {
            p = vehiculos.findById(t.uuid)
            p?.apply {
                mark = t.mark
                model = t.model
                deleted = t.deleted.toBoolean()
                engine = t.engine
            }
            exito = true
        }
        println(p)
        return exito
    }

    override fun create(t: Vehiculo): Vehiculo? {
        var p: Vehiculo? = null
        transaction {
            p = vehiculos.new(t.uuid) {
                mark = t.mark
                model = t.model
                deleted = t.deleted.toBoolean()
                engine = t.engine
                createAt = t.createAt
                dateVenta = t.dateVenta
                personaId = t.personaId


            }.toVehiculo()

        }
        return p
    }

    /**
     * funcion que filtra los coches por la clave foranea
     */
    fun findByPersonaId(personaId: Long): List<Vehiculo> {
        var lista = ArrayList<Vehiculo>()
        transaction {
            vehiculos.find { VehiculoTable.personaId eq personaId } // Select * ... where personaId = ?
                .forEach { lista.add(it.toVehiculo())}
        }
        return lista
    }

}

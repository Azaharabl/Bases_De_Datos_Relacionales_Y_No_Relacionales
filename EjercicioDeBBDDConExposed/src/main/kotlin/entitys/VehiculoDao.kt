package entitys

import entitys.PersonaDao.Companion.referrersOn
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Table.Dual.nullable
import org.jetbrains.exposed.sql.Table.Dual.references
import java.util.UUID

class VehiculoDao(uuid: EntityID<UUID>) : UUIDEntity(uuid) {

    // mi id será el de la tabla...
    companion object : UUIDEntityClass<VehiculoDao>(VehiculoTable)

    var mark by VehiculoTable.mark
    var model by VehiculoTable.model
    var dateVenta by VehiculoTable.dateVenta
    var engine by VehiculoTable.engine
    var createAt by VehiculoTable.createAt
   // var updateAt by VehiculoTable.updateAt
    var deleted by VehiculoTable.deleted

    // Agregar la columna de referencia a la tabla PersonasTable
    var personaId by VehiculoTable.personaId
}


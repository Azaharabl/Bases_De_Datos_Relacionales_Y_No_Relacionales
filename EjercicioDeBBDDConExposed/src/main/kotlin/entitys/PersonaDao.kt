package entitys

import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PersonaDao(id: EntityID<Long>) : LongEntity(id) {

    // mi id será el de la tabla...
    companion object : LongEntityClass<PersonaDao>(PersonasTable)

    var nombre by PersonasTable.nombre
    var fechaCarnet by PersonasTable.fechaCarnet
}


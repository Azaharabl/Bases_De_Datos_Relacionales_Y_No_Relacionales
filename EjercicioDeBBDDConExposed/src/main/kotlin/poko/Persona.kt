package poko

import entitys.PersonasTable
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID
import java.time.LocalDate

class Persona(
    var personaId : Long = 0,
    var nombre : String = "",
    var fechaCarnet :String = LocalDate.now().toString(),
    //var fechaCarnet : LocalDate = LocalDate.now()

)
{
    override fun toString(): String {
        return "\n Persona(personaId=$personaId, nombre='$nombre', fechaCarnet='$fechaCarnet')"
    }
}
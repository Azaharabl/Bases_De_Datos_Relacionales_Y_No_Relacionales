package poko


import entitys.PersonaDao
import java.time.LocalDate
import java.util.UUID

class Vehiculo (
    var uuid : UUID = UUID.randomUUID(),
    var mark : String = "marca",
    val model : String = "modelo",
    val dateVenta : String = LocalDate.now().toString(),
    val engine : String = "engine",
    val createAt : String = LocalDate.now().toString(),
    //val updateAt: LocalDate = LocalDate.now(),
    val deleted : String = "deleted",

    //unir
    val personaId: Long? = null



){
    override fun toString(): String {
        return "\n Vehiculo(uuid=$uuid, mark='$mark' $dateVenta ,personaId=  $personaId)"
    }
}
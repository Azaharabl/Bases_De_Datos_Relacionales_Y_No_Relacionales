package entyties

import java.time.LocalDate
import java.util.*

class Vehiculo (
    var id : String = UUID.randomUUID().toString(),
    var mark : String = "marca",
    val model : String = "modelo",
    val dateVenta : String = LocalDate.now().toString(),
    val engine : String = "engine",
    val createAt : String = LocalDate.now().toString(),
    val deleted : String = "deleted",

    //unir con relaciones referenciadas
    var personaId: String? = null



){
    override fun toString(): String {
        return "\n Vehiculo(id=$id, mark='$mark' alquilado a : $personaId )"
    }
}
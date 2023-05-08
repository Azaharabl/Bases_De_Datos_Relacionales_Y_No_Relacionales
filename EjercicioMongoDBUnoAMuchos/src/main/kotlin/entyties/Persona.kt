package entyties

import java.util.UUID


class Persona (
    var uuid: String = UUID.randomUUID().toString(),
    var nombre : String ,
    var edad : Int = 18
)
{
    override fun toString(): String {
        return "\n Persona(uuid='$uuid', nombre='$nombre', edad=$edad)"
    }
}

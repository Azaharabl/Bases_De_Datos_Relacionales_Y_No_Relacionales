package maper

import entyties.Persona
import org.bson.Document


fun Persona.toDocument(): Document? {
    val doc = Document()
    doc.append("uuid", this.uuid)
    doc.append("nombre", this.nombre)
    doc.append("edad", this.edad)
    return doc
}

fun Document.toPersona(): Persona? {

    val uuid = this.get("uuid")?.toString()
    val nombre = this.get("nombre")?.toString()
    val edad =this.get("edad") as Int? ?: return null
    if (uuid == null || nombre == null || edad == null){
        return null
    }
    return Persona(uuid, nombre, edad)
}


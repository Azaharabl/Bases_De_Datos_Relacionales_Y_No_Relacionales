package maper

import entyties.Vehiculo
import org.bson.Document

fun Vehiculo.ToDocument():Document{
     var doc = Document("id", this.id)
        .append("mark", this.mark)
        .append("model", this.model)
        .append("dateVenta", this.dateVenta)
        .append("engine", this.engine)
        .append("createAt", this.createAt)
        .append("deleted", this.deleted)
         .append("persona_id", this.personaId)
    return doc
}

fun Document.toVehiculo():Vehiculo?{
    var id = this.getString("id")
    var mark = this.getString("mark")
    var model = this.getString("model")
    var dateVenta = this.getString("dateVenta")
    var engine = this.getString("engine")
    var createAt = this.getString("createAt")
    var deleted = this.getString("deleted")
    var persona = this.getString("persona_id")

    //como el id de persona pude ser null
    // ya que hay coches sin vender y puden no pertenecer a una persona no lo comprobamos
    if(id == null || mark == null || model == null || dateVenta == null
        || engine == null || createAt == null || deleted == null){
        return null
    }
    return  Vehiculo( id, mark, model, dateVenta, engine, createAt,deleted, persona)

}

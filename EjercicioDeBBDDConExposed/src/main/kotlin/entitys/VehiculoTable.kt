package entitys



import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

object VehiculoTable  : UUIDTable(){
    val mark = varchar("mark",50)
    val model = varchar("model",50)
    val dateVenta = varchar("dateVenta",50)
    val engine = varchar("engine",50)
    val createAt = varchar("createAt",50)
    //val updateAt = date("updateAt")
    val deleted= bool("deleted")

    // relacion 1 - M (1 -1 .uniqueIndex)
    //relacion uno a uno que al borrar la persona se pone a null
    val personaId = long("personaId")       //nombre de la columna
        .references(PersonasTable.id,             //nombre de la tabla  y colummna a la que referencia
            onDelete = ReferenceOption.SET_NULL,  //referencia automatica al borrar
            onUpdate = ReferenceOption.CASCADE)   //referencia automatica cambia al cambiar la persona
        .nullable()                               //puede ser nulo
        //.uniqueIndex()                             //con esto resringimos 1 - 1 la relación

}

package entitys


import org.jetbrains.exposed.dao.id.LongIdTable



object PersonasTable : LongIdTable() {
    val nombre = varchar("nombre", 50)
    val fechaCarnet = varchar("fechaCarnet", 50)

}
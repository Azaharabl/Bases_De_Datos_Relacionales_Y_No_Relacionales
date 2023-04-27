package model

import java.time.LocalDate
import java.util.UUID

data class Car(
    var id : Long = 0,
    var uuid: String,
    var mark : String,
    var model : String,
    var date : LocalDate,
    var engine : Engine,
    var createAt : LocalDate,
    var updateAt : LocalDate,
    var deleted: Boolean

) {
    override fun toString(): String {
        return "Car(id=$id, uuid='$uuid', mark='$mark', model='$model', date=$date, engine=$engine, createAt=$createAt, updateAt=$updateAt, deleted=$deleted)"
    }
}
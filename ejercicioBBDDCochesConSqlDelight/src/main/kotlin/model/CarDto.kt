package model

import java.time.LocalDate

class CarDto(
    var id : Long = 0,
    var uuid: String,
    var mark : String,
    var model : String,
    var date : String,
    var engine : String,
    var createAt : String,
    var updateAt : String,
    var deleted: String

) {
    override fun toString(): String {
        return "CarDto(id=$id, uuid='$uuid', mark='$mark', model='$model', date='$date', engine='$engine', createAt='$createAt', updateAt='$updateAt', deleted='$deleted')"
    }
}

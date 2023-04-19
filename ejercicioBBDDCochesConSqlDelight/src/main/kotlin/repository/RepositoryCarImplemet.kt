package repository

import database.CarDto
import parser.equalsCar
import repository.RepositoryCar
import service.SqlDelightClient
import java.time.LocalDate

class RepositoryCarImplemet : RepositoryCar {
    val db = SqlDelightClient.queries


override fun findAll(): List<CarDto> {
    return  db.findAll().executeAsList()
}


override fun dropById(id: Long):Boolean {
    db.dropById(id)
    if (findById(id)==null){return true}else{return false}
}

override fun findById(id: Long): CarDto? {
    return db.findById(id).executeAsOneOrNull()
}

override fun findByUuid(uuid: String): CarDto? {
    return db.findByUuid(uuid).executeAsOneOrNull()
}

override fun updateByUuid(car: CarDto): Boolean {
    var actualizada = LocalDate.now().toString()
    var carAActualizar = car.copy(updateAt = actualizada)
    db.updateById(car.uuid, car.mark, car.model, car.date, car.engine, car.createAt
        , actualizada, car.deleted, car.id)
    var carSave = db.findById(car.id).executeAsOneOrNull()
    if (carSave?.equalsCar(carAActualizar) == true){
        return true
    }
        return true

}

override fun exixstsById(id: Long): Boolean {
    var coche = db.findById(id).executeAsOneOrNull()
    if (coche == null) {
        return false
    }
    return true
}

override fun create(car: CarDto): Int {
    db.create(car.uuid, car.mark, car.model, car.date, car.engine, car.createAt
        , car.updateAt, car.deleted)

   if( db.findByUuid(car.uuid).executeAsOneOrNull() ==null){return 0}else{return 1}
}


}

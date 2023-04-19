package parser

import database.CarDto
import model.Car
import model.Engine
import java.time.LocalDate


fun Car.ToCarDto(): CarDto {
        return CarDto(
            id = id,
            uuid = uuid,
            mark = mark,
            model = model,
            date = date.toString(),
            engine = engine.toString(),
            createAt = createAt.toString(),
            updateAt = updateAt.toString(),
            deleted = deleted.toString())
    }

fun CarDto.ToCar(): Car {
    return Car(
        id = id,
        uuid = uuid,
        mark = mark,
        model = model,
        date = LocalDate.of(
            date.trim().split("-")[0].toInt(),
            date.trim().split("-")[1].toInt(),
            date.trim().split("-")[2].toInt()),
        engine = Engine.valueOf(engine),
        createAt  = LocalDate.of(
            createAt.trim().split("-")[0].toInt(),
            createAt.trim().split("-")[1].toInt(),
            createAt.trim().split("-")[2].toInt()),
        updateAt =  LocalDate.of(
            updateAt.trim().split("-")[0].toInt(),
            updateAt.trim().split("-")[1].toInt(),
            updateAt.trim().split("-")[2].toInt()),
        deleted = deleted.toBoolean())
}
fun CarDto.equalsCar(other: CarDto?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    if (id != other.id) return false
    if (uuid != other.uuid) return false
    if (mark != other.mark) return false
    if (model != other.model) return false
    if (date != other.date) return false
    if (engine != other.engine) return false
    if (createAt != other.createAt) return false
    if (updateAt != other.updateAt) return false
    if (deleted != other.deleted) return false

    return true
}

package parser

import model.Car
import model.CarDto
import model.Engine
import java.time.LocalDate


fun Car.CarToCarDto(): CarDto {
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

fun CarDto.CarDtotoCar(): Car {
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


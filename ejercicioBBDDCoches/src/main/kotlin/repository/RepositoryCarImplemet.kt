package repository

import model.CarDto
import service.DatabaseManager
import java.sql.Statement

class RepositoryCarImplemet():RepositoryCar {


    override fun save(car: CarDto): Int {
        var res = 0
        if (exixstsById(car.id)){
            println("el coche con id ${car.id} ya exite en la bbdd por lo que guardamos la modificacion")
            var ok = updateByUuid(car)
            if(ok){res = 1;}
        }else{
           res= create(car)
        }
        return res
    }

    override fun findAll(): List<CarDto> {
        var sql = "SELECT * FROM cars"
        val list = ArrayList<CarDto>()
        var ps = DatabaseManager.db.prepareStatement(sql).use { s ->
            var res = s.executeQuery()
            while (res.next()) {
                var carDto = CarDto(
                    id = res.getLong(1),
                    uuid = res.getString(2),
                    mark = res.getString(3),
                    model = res.getString(4),
                    date = res.getString(5),
                    engine = res.getString(6),
                    createAt = res.getString(7),
                    updateAt = res.getString(8),
                    deleted = res.getString(9)
                )
                list.add(carDto)
            }
        }
        return list
    }

    override fun dropById(id: Long):Boolean {
        var sql = "DELETE FROM cars WHERE id =?"
        var res = 0
        var st = DatabaseManager.db.prepareStatement(sql).use { s ->
            s.setLong(1, id)
            res = s.executeUpdate()
        }
        return res > 0
    }

    override fun findById(id: Long): CarDto? {
        var sql = "SELECT * FROM cars WHERE id =?"
        val list = ArrayList<CarDto>()
        var ps = DatabaseManager.db.prepareStatement(sql).use { s ->
            s.setLong(1,id)
            var res = s.executeQuery()
            while (res.next()) {
                list.add(CarDto(
                    id = res.getLong(1),
                    uuid = res.getString(2),
                    mark = res.getString(3),
                    model = res.getString(4),
                    date = res.getString(5),
                    engine = res.getString(6),
                    createAt = res.getString(7),
                    updateAt = res.getString(8),
                    deleted = res.getString(9)))
            }
        }
        return list.firstOrNull()
    }

    override fun findByUuid(uuid: String): CarDto? {
        var sql = "SELECT * FROM cars WHERE uuid =?"
        val list = ArrayList<CarDto>()
        var ps = DatabaseManager.db.prepareStatement(sql).use { s ->
            s.setString(1,uuid)
            var res = s.executeQuery()
            while (res.next()) {
                list.add(CarDto(
                    id = res.getLong(1),
                    uuid = res.getString(2),
                    mark = res.getString(3),
                    model = res.getString(4),
                    date = res.getString(5),
                    engine = res.getString(6),
                    createAt = res.getString(7),
                    updateAt = res.getString(8),
                    deleted = res.getString(9)))
            }
        }
        return list.firstOrNull()
    }

    override fun updateByUuid(car: CarDto): Boolean {
        var sql = "UPDATE cars SET uuid =?, mark =?, model =?, date =?, engine =?," +
                " createAt =?, updateAt =?, deleted =? WHERE id =?"
        var res = 0
        var ps = DatabaseManager.db.prepareStatement(sql).use { s ->
            s.setString(1,car.uuid)
            s.setString(2,car.mark)
            s.setString(3,car.model)
            s.setString(4,car.date)
            s.setString(5,car.engine)
            s.setString(6,car.createAt)
            s.setString(7,car.updateAt)
            s.setString(8,car.deleted)
            s.setLong(9,car.id)
            res = s.executeUpdate()
        }
        return res > 0
    }

    override fun exixstsById(id: Long): Boolean {
        var coche = findById(id)
        if (coche == null) {
            return false
        }
        return true
    }

    override fun create(car: CarDto): Int {
        var res = 0
        var id: Long = 0
        val sql =
            """INSERT INTO cars (uuid, mark, model, date, engine, createAt, updateAt, deleted) VALUES (?,?,?,?,?,?,?,?)"""

        var ps = DatabaseManager.db.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS).use { s ->

            s.setString(1, car.uuid)
            s.setString(2, car.mark)
            s.setString(3, car.model)
            s.setString(4, car.date)
            s.setString(5, car.engine)
            s.setString(6, car.createAt)
            s.setString(7, car.updateAt)
            s.setString(8, car.deleted)

            res = s.executeUpdate()

            // Obtengo la clave porque es autoincremental
            var key = s.generatedKeys
            if (key.next()) {
                id = key.getLong(1)
            }
            println("insertamos con id : " +id)
            return res
        }
    }



}
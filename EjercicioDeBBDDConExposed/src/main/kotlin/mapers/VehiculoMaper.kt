package mapers

import entitys.VehiculoDao
import poko.Vehiculo

fun VehiculoDao.toVehiculo():Vehiculo{
    return Vehiculo(
        uuid = this.id.value,
        mark = this.mark,
        model = this.model,
        engine = this.engine,
        deleted = this.deleted.toString(),
        createAt =  this.createAt,
        //updateAt = this.updateAt,
        dateVenta = this.dateVenta,
        //referenciar
       personaId = this.personaId
    )
}
package controller

import cache.CarCacheManager
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.onFailure
import database.CarDto
import errors.CarErrors
import model.Car
import parser.ToCar
import parser.ToCarDto
import repository.RepositoryCar
import repository.RepositoryCarImplemet
import storage.CsvStorage
import storage.JsonStorage
import storage.Storage
import validators.ValidarorCar

class CarController (
    val storageCarCsv : Storage = CsvStorage(), //entrada de datos
    val storageCarJson : Storage = JsonStorage(),   //salida de datos
    val repositoryCar  : RepositoryCar =  RepositoryCarImplemet()    //bbdd


){
    fun getAllCarr(): List<Car> {
        //obtenemos del Resositorio ya que no sabremos si la cache está completa
        var carsDto = repositoryCar.findAll()
        var cars = carsDto.map { it.ToCar() }
        //insertamos datos en la cache
        cars.forEach { CarCacheManager.cache.put(it.id,it.ToCarDto())  }
        return cars
    }
    fun dropAllCars(){
        //borramos en el repositorio y en la cache
        repositoryCar.findAll().stream().forEach {repositoryCar.dropById(it.id)}
        CarCacheManager.cache.invalidateAll()
    }
    fun saveCar(car: Car): Result<Car, CarErrors> {
        var result : Result<Car, CarErrors>
        if (repositoryCar.exixstsById(car.id)){
            println("el coche con id ${car.id} ya exite en la bbdd por lo que guardamos la modificacion")
            //todo no va
            var ok = repositoryCar.updateByUuid(car.ToCarDto())
            if(ok){
                result = Ok(car)
                CarCacheManager.cache.put(car.id,car.ToCarDto())
            }else{
                result = Err(CarErrors.CarNotSaved("el coche no ha podido ser guardado"))
            }
        }else{
            var res= repositoryCar.create(car.ToCarDto())
            if(res>0){
                var carUpdate = repositoryCar.findByUuid(car.uuid)
                if (carUpdate != null) {
                    CarCacheManager.cache.invalidate(car.id)
                    CarCacheManager.cache.put(carUpdate.id,carUpdate)
                    result = Ok(carUpdate.ToCar())
                }
            }
            result = Err(CarErrors.CarNotSaved("el coche no ha podido ser guardado ni actualizado"))
        }
        return result

    }
    fun deleteCar(carId: Long): Result<Boolean, CarErrors> {
        if(repositoryCar.dropById(carId)){
            //borramos de la cache
            CarCacheManager.cache.invalidate(carId)
            return Ok(true)
        }else{
            return Err(CarErrors.carNotFound("el coche no ha posido ser borrado"))
        }
    }
    fun saveAllCars(carList: List<Car>) {
        //implementamos la cache en el create o update, no aquí
        carList.forEach { saveCar(it) }
    }
    fun saveAllCarsToJson(url: String, cars : List<Car>): Result<Boolean, CarErrors> {
        cars.forEach { it.ToCarDto().ValidarorCar().onFailure { return Err(CarErrors.carNotValid(it.message)) }}
        val carDto =cars.map {it.ToCarDto()}
        storageCarJson.writeCarDto(url,carDto.toList())
        return Ok(true)
    }
    fun getCarByid(id:Long): Result<Car, CarErrors> {
        //comprobamos si esta en caché y si no si esta en bbdd
        var car = CarCacheManager.cache.get(id)
        if(car == null){
            // no está en la cache , buscamos en el repositorio
            car = repositoryCar.findById(id)
            if (car != null) {
                return Ok(car.ToCar())
            }
            return Err(CarErrors.carNotFound("car no encontrado con id: $id"))
        }
        return Ok(car.ToCar())
    }
    fun getCarByUuid(uuid:String): Result<Car, CarErrors> {
        var car : CarDto? = repositoryCar.findByUuid(uuid)
        if (car != null) {
            return Ok(car.ToCar())
        }else{return Err(CarErrors.carNotFound("car no encaontrado con uuid: $uuid"))
        }
    }
    fun initCarsFormCsv(url : String, deleteBefore : Boolean) {
        if (deleteBefore == true) {
            dropAllCars()
        }
        println("leemos datos de el csv")
        saveAllCars(storageCarCsv.readCarDto(url).map { it.ToCar() })

    }
}
















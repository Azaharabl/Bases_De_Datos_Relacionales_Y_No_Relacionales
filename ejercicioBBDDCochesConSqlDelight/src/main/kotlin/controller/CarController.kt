package controller

import cache.CarCacheManager
import com.github.michaelbull.result.*
import database.AppDatabaseQueries
import database.Cars
import errors.CarErrors
import storage.CsvStorage
import storage.JsonStorage
import service.SqlDelightClient
import storage.Storage
import validators.ValidarorCar


class CarController (
    val storageCarCsv : Storage = CsvStorage(), //entrada de datos
    val storageCarJson : Storage = JsonStorage(),   //salida de datos
    val repositoryCar: AppDatabaseQueries =  SqlDelightClient.queries



){
    /**
    fun getAllCarr(): List<Car> {
        //obtenemos del Resositorio ya que no sabremos si la cache está completa
       var cars = repositoryCar.findAll()
        //insertamos datos en la cache
        cars.forEach {CarCacheManager.cache.put(it.id,it.CarToCarDto())  }
        return cars
    }
    fun dropAllCars(){
        //borramos en el repositorio y en la cache
        var allCars = repositoryCar.findAll().execute().g
            //.stream().forEach {repositoryCar.dropById(it.id)}
        CarCacheManager.cache.invalidateAll()
    }
    fun saveCar(car: Car):Result<Car, CarErrors>  {
       var result :Result<Car, CarErrors>
        if (repositoryCar.exixstsById(car.id)){
            println("el coche con id ${car.id} ya exite en la bbdd por lo que guardamos la modificacion")
            var ok = repositoryCar.updateByUuid(car.CarToCarDto())
            if(ok){
                result = Ok(car)
                CarCacheManager.cache.put(car.id,car.CarToCarDto())
            }else{
                result = Err(CarErrors.CarNotSaved("el coche no ha podido ser guardado"))
            }
        }else{
            var res= repositoryCar.create(car.CarToCarDto())
            if(res>0){
                var carUpdate = repositoryCar.findByUuid(car.uuid)
                if (carUpdate != null) {
                    CarCacheManager.cache.invalidate(car.id)
                    CarCacheManager.cache.put(carUpdate.id,carUpdate)
                    result = Ok(carUpdate.CarDtotoCar())
                }
            }
           result = Err(CarErrors.CarNotSaved("el coche no ha podido ser guardado ni actualizado"))
        }
        return result

    }




    }
    */
    fun saveAllCars(carList: List<Cars>) {
        //implementamos la cache en el create o update, no aquí
        carList.map { it }.forEach {saveCars(it)
        }
    }

    fun getCarsByUuid(uuid:String):Result<Cars, CarErrors> {
        var car : Cars? = repositoryCar.findByUuid(uuid).executeAsOneOrNull()
        if (car != null) {
            return Ok(car)
        }else{return Err(CarErrors.carNotFound("car no encaontrado con uuid: $uuid"))}
    }

    fun initCarsFormCsv(url : String, deleteBefore : Boolean) {
        if (deleteBefore == true) {
           //todo  dropAllCars()
        }
        println("leemos datos de el csv")
        saveAllCars(storageCarCsv.readCars(url).map { it }.toList())//todo

    }

    fun getCarsById(id:Long):Result<Cars, CarErrors> {
        var car : Cars? = repositoryCar.findById(id).executeAsOneOrNull()
        if (car != null) {
            return Ok(car)
        }else{return Err(CarErrors.carNotFound("car no encaontrado con id: $id"))}
    }

    fun getAllCars(): List<Cars> {
        var cars = repositoryCar.findAll().executeAsList()
         cars.forEach {CarCacheManager.cache.put(it.id,it)  }
        return cars
    }

    fun deleteCar(carId: Long):Result<Boolean, CarErrors>  {
        repositoryCar.dropById(carId)
        getCarsById(carId)
            .onSuccess {
                return Err(CarErrors.carNotFound("el coche no ha posido ser borrado")) }
            .onFailure {
                CarCacheManager.cache.invalidate(carId)
                return Ok(true) }

        //si da otro error
        return Err(CarErrors.carNotFound("el coche no ha posido ser borrado"))
    }

    fun saveCars(cars: Cars): Result<Boolean, CarErrors> {
        getCarsById(cars.id)
            .onSuccess {
                 updateCars(it)
                     .onSuccess { return Ok(it) }
                     .onFailure { return Err(CarErrors.CarNotSaved("el coche no ha posido ser bor")) }
            }
            .onFailure {
                createCars(cars)
                    .onSuccess { return Ok(it) }
                    .onFailure { return Err(CarErrors.CarNotSaved("el coche no ha posido ser bor")) }

            }
        return Err(CarErrors.CarNotSaved("el coche no ha posido ser bor"))

    }

    private fun createCars(cars: Cars): Result<Boolean, CarErrors> {
        println("creando cohce en bbdd")

        repositoryCar.create(cars.uuid, cars.mark, cars.model,
            cars.date, cars.engine, cars.createAt, cars.updateAt, cars.deleted)
        if(repositoryCar.findById(cars.id).executeAsOneOrNull()==null){
            return Err(CarErrors.CarNotSaved("el coche no ha posido ser guardado"))
        }
        return Ok(true)

    }

    private fun updateCars(cars: Cars): Result<Boolean, CarErrors> {
        println("modificando cohce en bbdda")
        //todo esto es lo unico que falla
        repositoryCar.updateById(cars.uuid, cars.mark, cars.model,
            cars.date, cars.engine, cars.createAt, cars.updateAt, cars.deleted,cars.id )

        return Ok(true)
    }

    fun dropAllCars() {
        getAllCars().forEach { deleteCar(it.id) }
    }

    fun saveAllCarsToJson(url: String, cars : List<Cars>):Result<Boolean,CarErrors> {
        cars.forEach { it.ValidarorCar().onFailure { return Err(CarErrors.carNotValid(it.message)) }}
        storageCarJson.writeCars(url,cars)
        return Ok(true)
    }

}






















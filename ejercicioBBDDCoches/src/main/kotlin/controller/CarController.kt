package controller

import com.github.michaelbull.result.*
import errors.CarErrors
import model.Car
import repository.RepositoryCar
import repository.RepositoryCarImplemet
import storage.CsvStorage
import storage.JsonStorage
import model.CarDto
import parser.CarDtotoCar
import parser.CarToCarDto
import storage.Storage
import validators.ValidarorCar


class CarController (
    val storageCarCsv : Storage = CsvStorage(), //entrada de datos
    val storageCarJson : Storage = JsonStorage(),   //salida de datos
    val repositoryCar  :RepositoryCar =  RepositoryCarImplemet()    //bbdd

){
    fun getAllCarr(): List<Car> {
        return repositoryCar.findAll().map {it.CarDtotoCar()}
    }
    fun dropAllCars(){
        repositoryCar.findAll().stream().forEach {repositoryCar.dropById(it.id)}
    }
    fun saveCar(car: Car):Result<Int, CarErrors>  {

        return car.ValidarorCar().andThen{ Ok(repositoryCar.save(car.CarToCarDto())) }

    }
    fun deleteCar(carId: Long):Result<Boolean, CarErrors>  {
        if(repositoryCar.dropById(carId)){
            return Ok(true)
        }else{
            return Err(CarErrors.carNotFound("el coche no ha posido ser borrado"))
        }
    }
    fun saveAllCars(carList: List<Car>) {
       carList.forEach { saveCar(it) }
    }
    fun saveAllCarsToJson(url: String, cars : List<Car>):Result<Boolean,CarErrors> {
        cars.forEach { it.ValidarorCar().onFailure { return Err(CarErrors.carNotValid(it.message)) }}
        val carDto =cars.map {it.CarToCarDto()}
        storageCarJson.writeCarDto(url,carDto.toList())
        return Ok(true)
    }
    fun getCarByid(id:Long):Result<Car, CarErrors> {
        var car = repositoryCar.findById(id)
        if (car != null) {
            return Ok(car.CarDtotoCar())
        }else{
            return Err(CarErrors.carNotFound("car no encontrado con id: $id"))
        }
    }
    fun getCarByUuid(uuid:String):Result<Car, CarErrors> {
        var car : CarDto? = repositoryCar.findByUuid(uuid)
        if (car != null) {
            return Ok(car.CarDtotoCar())
        }else{return Err(CarErrors.carNotFound("car no encaontrado con uuid: $uuid"))}
    }
    fun initCarsFormCsv(url : String, deleteBefore : Boolean) {
        if (deleteBefore == true) {
            dropAllCars()
        }
        println("leemos datos de el csv")
        storageCarCsv.readCarDto(url).forEach {
            repositoryCar.save(it) }

    }
}
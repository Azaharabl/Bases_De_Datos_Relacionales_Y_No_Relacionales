package storage

import database.Cars
import model.Car
import model.CarDto

interface Storage {

    fun readCars(url : String): ArrayList<Cars>
    fun writeCars(url: String, cars: List<Cars>): Boolean
}
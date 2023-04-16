package storage

import model.Car
import model.CarDto

interface Storage {

    fun readCarDto(url : String): ArrayList<CarDto>
    fun writeCarDto(url: String, cars: List<CarDto>): Boolean
}
package storage

import database.CarDto


interface Storage {

    fun readCarDto(url : String): ArrayList<CarDto>
    fun writeCarDto(url: String, cars: List<CarDto>): Boolean
}
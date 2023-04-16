package storage

import model.CarDto
import java.io.File
import kotlin.collections.ArrayList

class CsvStorage : Storage {
    override fun readCarDto(url: String): ArrayList<CarDto> {

        println("url: " + url)

        if (!File(url).exists()){
            println("el fichero no exixte")
            return ArrayList()
        }else if(!File(url).canRead()) {
            println(" el fichero no se puede leer")
            return ArrayList()
        }
        val cars = ArrayList<CarDto>()
        try {
            println("leemos lineas")
            var lista = File(url).readLines().drop(1).map { s -> getCarDto(s) }
            cars.addAll(lista)

        } catch (e: Exception) {
            println("ha habido un error" + e.printStackTrace())
        }
        return cars

    }

    override fun writeCarDto(url: String, cars: List<CarDto>): Boolean {
        TODO("Not yet implemented")
    }

     private fun getCarDto (s : String): CarDto {

        var campo =s.split(",")
        return CarDto(
            0L,
            campo[0].trim(),
            campo[1].trim(),
            campo[2].trim(),
            campo[3].trim(),
            campo[4].trim(),
            campo[5].trim(),
            campo[6].trim(),
            campo[7].trim()
        )



    }
}


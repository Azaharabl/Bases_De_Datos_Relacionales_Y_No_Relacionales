package storage

import database.Cars
import model.CarDto
import java.io.File
import kotlin.collections.ArrayList

class CsvStorage: Storage {
    override fun readCars(url: String): ArrayList<Cars> {

        println("url: " + url)

        if (!File(url).exists()){
            println("el fichero no exixte")
            return ArrayList()
        }else if(!File(url).canRead()) {
            println(" el fichero no se puede leer")
            return ArrayList()
        }
        val cars = ArrayList<Cars>()
        try {
            println("leemos lineas")
            var lista = File(url).readLines().drop(1).map { s -> getCars(s) }
            cars.addAll(lista)

        } catch (e: Exception) {
            println("ha habido un error" + e.printStackTrace())
        }
        return cars

    }

     override fun writeCars(url: String, cars: List<Cars>): Boolean {
        TODO("Not yet implemented")
    }

     private fun getCars (s : String): Cars {

        var campo =s.split(",")
        return Cars(
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


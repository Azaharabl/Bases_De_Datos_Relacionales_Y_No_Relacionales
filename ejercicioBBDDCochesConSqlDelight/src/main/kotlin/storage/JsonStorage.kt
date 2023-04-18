package storage

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import database.Cars
import model.CarDto
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class JsonStorage : Storage {
    override fun readCars(url: String): ArrayList<Cars> {
        TODO("Not yet implemented")
    }

    override fun writeCars(url: String, cars: List<Cars>): Boolean {
        println(url)
        if (!File(url).exists()) {
            Files.createFile(Paths.get(url))
        }

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val adapter: JsonAdapter<List<Cars>> = moshi.adapter(Types.newParameterizedType(List::class.java, Cars::class.java))

        var carsDto = cars.stream().toList()

        println("llevamos a json ${carsDto.size}")
        //  pretty print
        File(url).writeText(adapter.indent("  ").toJson(cars))
        return true

    }
}
package storage

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import database.CarDto

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class JsonStorage : Storage {
    override fun readCarDto(url: String): ArrayList<CarDto> {
        TODO("Not yet implemented")
    }

    override fun writeCarDto(url: String, cars: List<CarDto>): Boolean {
        println(url)
        if (!File(url).exists()) {
            Files.createFile(Paths.get(url))
        }

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val adapter: JsonAdapter<List<CarDto>> = moshi.adapter(Types.newParameterizedType(List::class.java, CarDto::class.java))

        var carsDto = cars.stream().toList()

        println("llevamos a json ${carsDto.size}")
        //  pretty print
        File(url).writeText(adapter.indent("  ").toJson(carsDto))
        return true

    }
}
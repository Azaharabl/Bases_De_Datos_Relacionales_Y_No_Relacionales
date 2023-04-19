package cache
import database.CarDto
import io.github.reactivecircus.cache4k.Cache


object CarCacheManager {


    val doRefres: Boolean = false // Si queremos que se refresque el cache
    val refreshTime = 60 * 60 * 1000L // 1 hora en milisegundos
    val size = 100

    // Creamos la caché y configuramos a medida
    val cache = Cache.Builder().maximumCacheSize(100)
        .build<Long, CarDto>()

}




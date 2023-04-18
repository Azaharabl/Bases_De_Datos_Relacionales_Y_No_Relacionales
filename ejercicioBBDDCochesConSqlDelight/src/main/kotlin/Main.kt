import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import config.AppConfig
import controller.CarController
import database.Cars
import model.Car
import service.SqlDelightClient
import java.io.File
import java.nio.file.Paths


fun main(args: Array<String>) {
    println("Hello sql lite World!")

    AppConfig.initConfig()
    val c = CarController()




    //obtenemos path de obtencion de datos
    val resource = ClassLoader.getSystemResource("config.properties")
    var pathCsv =  (Paths.get(resource.toURI()).parent).toString() + Paths.get(AppConfig.pathFilesCsv).toString()

    println(" obtenemos todos los datos y los metemos en la bbdd")
    c.initCarsFormCsv(pathCsv,true)

    println("encontramos uno por id 1")
    var car1 : Cars? = null
    c.getCarsById(1)
        .onSuccess { println(it) ; car1=it  }
        .onFailure { println(it.message) }

    println("encontramos uno por uuid ${car1?.uuid}")
    car1?.uuid?.let{c.getCarsByUuid(car1!!.uuid!!)
        .onSuccess { println(it) }
        .onFailure { println(it)}}

    println("imprimir los coches que tenemos en la bbdd" )
    println(c.getAllCars())

    println("buscamos uno que no puede estar para que de fallo id : -1")
    c.getCarsById(-1)
        .onSuccess { println(it) ; car1=it  }
        .onFailure { println(it.message) }


    println("borramos uno por id 1")
    car1?.let { it1 -> c.deleteCar(it1.id).onSuccess { println(it) } }?.onFailure { println(it.message) }

    println("imprimir los coches que tenemos en la bbdd para ver el que falta" )
    println( c.getAllCars())

    println("buscamos uno por id 2 para modificarlo cambiaremos el modelo")
    var carsModificado : Cars? = null
    c.getCarsById (2).onSuccess {
        println(it)
        carsModificado = it.copy(
            model = "modificado",
            mark = "modificada"
        )

        if (carsModificado!= null) {
            println(carsModificado)
            c.saveCars(carsModificado!!).onSuccess {
                println("modificamos en la bbdd")
                println(it) }.onFailure { println(it.message) }
        }


    }.onFailure { println(it.message) }

    println("imprimir los coches que tenemos en la bbdd para ver la modificacion" )
    println( c.getAllCars())

    println("borramos todos")
    c.dropAllCars()

    println("imprimir los coches que tenemos en la bbdd para ver que se han borrado" )
    var list = c.getAllCars()
    println(list)


    //volvemos a obtenemos todos los datos y los metemos en la bbdd
    c.initCarsFormCsv(pathCsv,true)

    println("imprimir los coches que tenemos en la bbdd para ver que se han vuelto a obtener" )
    list = c.getAllCars()
    println(list)


    var pathJson =  (Paths.get(resource.toURI()).parent).toString() + Paths.get(AppConfig.pathFilesJson+ File.separator + "fichero.json" ).toString()
    c.saveAllCarsToJson(pathJson,list)
        .onSuccess { println("fichero realizado conexito") }
        .onFailure { println(it.message) }



    /**




    System.exit(0)*/

}





package dbManager

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.async.SingleResultCallback
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients


object DBManager {



    //esta la podemos tener en el archivo . properties y leer desde ahí
    var mongoClient : MongoClient = MongoClients.create("mongodb://localhost:27017")

    // Obtener una referencia a la base de datos
    val database = mongoClient.getDatabase("AzaharaBBDD")


    fun reiniciarbbdd(){
        database.drop()
    }
    fun open(){
        mongoClient = MongoClients.create("mongodb://localhost:27017")
    }

    fun close(){
        if (mongoClient != null){
            mongoClient.close()
        }
    }


    //opcionales, solo para ver la creacion de las coleciones
    fun mostrarDatabases() {
        println("Databases en este momento son: ")
        println(mongoClient.listDatabaseNames().toList())
    }

    fun mostrarColeciones(){
        println("Coleciones en este momento son: ")
        println( database.listCollectionNames().toList())
    }


    fun crearColecion(nombreColecion: String){
        try {
            database.createCollection(nombreColecion)
            println("La colección $nombreColecion se creó correctamente")
        }catch (e : Exception){
            println("Error al crear la colección $nombreColecion seguramente ya está creada")
        }
    }

}
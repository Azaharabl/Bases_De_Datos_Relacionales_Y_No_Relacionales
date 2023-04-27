package database

import java.sql.DriverManager

object DatabaseManager {

    // Conectar a la base de datos
    val dbUrl = "jdbc:h2:~/mydb" // URL de conexión de H2
    val db = DriverManager.getConnection(dbUrl, "Azahara", "") // conexión con la base de datos

}

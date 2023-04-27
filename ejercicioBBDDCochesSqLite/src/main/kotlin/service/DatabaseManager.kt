package service

import config.AppConfig
import java.sql.DriverManager

object DatabaseManager {
    val db get() = DriverManager.getConnection(AppConfig.dbUrl)

    init {
        if(AppConfig.dbDropTables.toBoolean()){
            dropTables()
        }
        println("lellendo o creando la tabla")
        createTables()
        cerrarDB()
    }

    private fun dropTables() {
        val sql = "DROP TABLE IF EXISTS cars"
        db.use {    //abre y cierra la conexion
            it.createStatement().use { s ->
                s.executeUpdate(sql)
            }
        }
    }

    private fun createTables() {
        val sql = """CREATE TABLE IF NOT EXISTS cars (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                uuid TEXT UNIQUE,
                mark TEXT,
                model TEXT,
                date TEXT,
                engine TEXT,
                createAt TEXT,
                updateAt TEXT,
                deleted TEXT
             )"""
        db.use {
            it.createStatement().use { s ->
                s.executeUpdate(sql)
            }
        }

    }
    fun cerrarDB() {
        db.close()
    }


}
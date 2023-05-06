package database


import entitys.PersonasTable
import entitys.VehiculoTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.io.FileInputStream
import java.nio.file.Path
import java.sql.DriverManager
import java.util.*

object DataBaseManager {

        fun init() {


            var p = Properties()
            p.load(FileInputStream(ClassLoader.getSystemResource("config.properties").file))

            val url = p.getProperty("jdbc.url")
            val driver =  p.getProperty("jdbc.driverclassname")
            val user = p.getProperty("jdbc.username")
            val password = p.getProperty("jdbc.password")

            Database.connect(url, driver, user, password)

            if (p.getProperty("jdbc.createTables").toBoolean() ) {
                createTables()
            }

        }

        private fun createTables(){
            transaction{
                SchemaUtils.create(PersonasTable, VehiculoTable)
            }
        }




    }
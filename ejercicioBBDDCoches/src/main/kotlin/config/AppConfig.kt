package config

import java.io.FileInputStream
import java.util.*

object AppConfig {

    lateinit var databaseUrl : String
    lateinit var databaseUser: String
    lateinit var databasePassword: String
    lateinit var pathFilesCsv: String
    lateinit var pathFilesJson: String
    lateinit var dataBaseDropTables : String



    fun initConfig(){
        //leer los resouces
        var propertiesFile = ClassLoader.getSystemResource("config.properties").file
        println(propertiesFile.toString())

            var properties = Properties()
            properties.load(FileInputStream(propertiesFile))

            databaseUrl = properties.getProperty("dbUrl")
            databaseUser = properties.getProperty("dbUser")
            databasePassword = properties.getProperty("dbPassword")
            pathFilesCsv = properties.getProperty("csvInputDir")
            pathFilesJson = properties.getProperty("jsonOutputDir")
            dataBaseDropTables = properties.getProperty("dataBaseDropTables")

    }
}



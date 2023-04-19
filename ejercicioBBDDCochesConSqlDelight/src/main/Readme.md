## Ejercicio de BBDD sqlite
ejercicio donde:
- Obtendremos unos datos de un Csv
- Uso de SqlDelight
- Creacion de BBDD sqlite
- Introduciremos datos en la BBDD
- realizacion de operaciones CRUD en BBDD
- Exportaremos en fichero json
- uso de Response
- Uso de Error para no hacer uso inecesario de exception

### Cambios realizados para hacer el ejercicio base sin SqlDeLite:

- Para el paso a sqlDelight no devemos de cambiar nada más que el Repositorio ya que el resto seguira identico.
- para este también podéis ver unas actualizaciones en cuanto a el paquete parser.Parsers.Kt ya que de este modo es más intuitiva la llamada a clases y añadido una funcion para comparar dos CarDto  

### Pasos:
- Importamos las dependencias necesarias para usar SqlDelight
  - // SqlDeLight, lo mejor es con SQLite para hacer las cosas reactivas o con corrutinas
    implementation("com.squareup.sqldelight:runtime:1.5.4")
  - // SQLite para SqlDeLight
    implementation("com.squareup.sqldelight:sqlite-driver:1.5.4")
  - // Para poder usar corrutias en SqlDeLight y conectarnos a la base de datos para cambios
    implementation("com.squareup.sqldelight:coroutines-extensions-jvm:1.5.4")

- Añadimos el plugin necesario
  - // SQLdelight
    id("com.squareup.sqldelight") version "1.5.4"
  
- Añadimos en el grandle las siguientes lineas, con los datos de nuestra db
  - sqldelight {
    - // Debemos colocarlo en el main/sqldelight/database/AppDatabase.sq
    - database("AppDatabase") {
    - packageName = "database" // Este es el paquete donde se genera el código en sqldelight
    - }
    - }
    
- En este punto tenemos que hacer el Fichero AppDatabase.sq 
  - en este tendremso el script de la creación de las Tablas y las de las funciones que queremos en el repositorio
  - IMPORTANTE Debemos colocarlo en el main/sqldelight/database/AppDatabase.sq

- Ahora se hace la ¡¡MAGIA!! 
  - Gradle > sqldelight > generateSqlDeligth > generateMainAppDatabase...
  - con esto tras la generación podemos ver que en Buil nos ha realizado:
    - una clase con el objeto de la bbdd
    - y las interfaces que necesitas para implementar el SqlClient que luego irá en el repositorio

- Creamos ahora el SqlCliente que en este repositorio lo puedes ver en mi paquete Service

- una vez realizado ya odemos usarlo en el repositorio para aceder a todas las querys creadas.

### Pasos realizados para hacer el ejercicio base sin SqlDeLite:
- Creamos Modelo Car
  - Con ello creamos tambien: 
    - CarDto
    - CarValidator
    - CarErros
    - CarParser
- Realizamos el Appconfig (Para leer fichero de propiedades y encapsular informacion)
- Creamos DatabaseManager (Para gestionar la conexion y crear las tablas de la BBDD)
- Creamos CarStorage para importar o exportar a ficheros
- Creamos CarRepository para aceder a la BBDD
- Relacionamos los anteriores con la creación de un CarControlador (M-V-C)

# Bases_De_Datos_Relacionales
Ejercicio de BBDD con SQLite, con operaciones CRUD y leyendo y exportando a ficheros. 

Ejercicio de BBDD SQLite donde:

- Obtendremos unos datos de un Csv
- Creacion de BBDD sqlite
- Introducióemos datos en la BBDD
- Realización de operaciones CRUD en BBDD
- Exportaremos en fichero json
- Uso de Response
- Uso de Error para no hacer uso inecesario de exception

### Pasos realizados:
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

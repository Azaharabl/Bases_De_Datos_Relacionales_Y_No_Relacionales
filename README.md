# Bases_De_Datos_Relacionales
1. Ejercicio de BBDD con SQLite, con operaciones CRUD y leyendo y exportando a ficheros. 
2. Ejercicio de BBDD con SqlDeLight + SQLite, con operaciones CRUD y leyendo y exportando a ficheros
3. Ejercicio de BBDD de H2 con Hibernate
4. Ejercicio de BBDD de H2 con Exposed relaciones 1-M , 1-1

## Ejercicio de BBDD de H2 con Exposed 
- Manejo de relaciones 1 - M
- Manejo de relaciones 1 - 1
- Crud
- Direcionalidad

## Ejercicio de BBDD SqlDeLight + BBDD SQLite :
- es una modificación del primer ejercicio pero usando SqlDelight para el manejo de las Querys de la BBDD

## Ejercicio de una BBDD de H2 con Hibernate
- Tenemos una relacion de OneToMany de Persona y Vehiculo
- Manejo de la BBDD a trabes de Hibernate

## Ejercicio de BBDD SQLite donde:

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



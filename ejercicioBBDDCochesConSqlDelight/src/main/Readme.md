## Ejercicio de BBDD sqlite
ejercicio donde:
- obtendremos unos datos de un Csv
- Creacion de BBDD sqlite
- Introduciremos datos en la BBDD
- realizacion de operaciones CRUD en BBDD
- Exportaremos en fichero json
- uso de Response
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

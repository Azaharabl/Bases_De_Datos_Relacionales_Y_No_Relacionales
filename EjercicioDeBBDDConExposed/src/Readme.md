## Ejercicio de bbdd con exposed
1. implementar las dependencias necesarias
   - exposed
   - h2
2. creamos el fichero de propiedades con los datos necesarios
3. creamos el database manager, este puede obtener los datos desde un objeto ManagerConfig , 
pero en este caso para agilizar acederemos a el fichero properties directamente desde aqui.
4. creamos las tablas / objetos que necesitemos con las características de Exposed
5. creamos la interfad que vamos a implementar en los repositorios y los repositorios
6. ahora implementamos las relaciones en todos los objetos que queremos
- Dao:
   - vehiculos: // Agregar la columna de referencia a la tabla PersonasTable
      - var personaId by VehiculoTable.personaId
- Poco : 
- Tabla: 
  -   val personaId = long("personaId")       //nombre de la columna
  -  .references(PersonasTable.id,            //nombre de la tabla  y colummna a la que referencia
  -   onDelete = ReferenceOption.SET_NULL,    //referencia automatica al borrar
  -   onUpdate = ReferenceOption.CASCADE)     //referencia automatica cambia al cambiar la persona
  -   .nullable()                             //puede ser nulo
  -   .uniqueIndex()                           //con esto resringimos 1 - 1 la relación

7. para tener vidirecionalidad hacemos la siguiente funcion para obtener todo los coches con id de una persona
- transaction { vehiculos.find { VehiculoTable.personaId eq personaId } .forEach { it.toVehiculo()).toList()} }
- para obtener la persona desde el coche usamos la funcion personas.findByID()


Referencias Útiles:
https://www.baeldung.com/kotlin/exposed-persistence

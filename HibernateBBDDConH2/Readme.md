1.  Necesitas agregar las dependencias de Hibernate
- implementation("org.hibernate:hibernate-core:5.5.7.Final")
- implementation("com.h2database:h2:1.4.200")

2. Implementa la dependencia de la bbdd que quieras en mi caso h2 
- implementation ("com.h2database:h2:1.4.200")

3. Crea un object HibernateManager desde donde cargaremos las propidades del fichero persistence y que será el aceso a la bbdd
- este leera el fichero que crearemos a continuacion y que tiene que llamarse igual que l nombre que le pongamos aqui.
- AzaharaBBDD en mi caso

4. Creamos un archivo persistence.xml con los datos de la bbdd y que de
   nombre tenga el mismo que nosotor le decimos en HibernateManager que tiene que cojer

5.  Crea un fichero aplicatión.properties donde tienen que estar todas las características de la bbdd que vas a crear

6. Creamoslas clses con las anitaciones de hivernate, estas tambientiene que estar añladidas al fichero persistence

7. Crea una clase para realizar operaciones CRUD en la base de datos. 
En el directorio "src/main/kotlin", crea un nuevo paquete llamado "repository" y crea una clase llamada 
- "PersonaRepository"
- "VehiculoRepository"

8. Si necesitas relacionar las tablas pudedes usar las anitaciones @Manytoone, @OnetoMany , @OneToOne
@OneToMany(mappedBy = "Vehiculo", cascade = CascadeType.ALL, orphanRemoval = false, optional = true)

- mappedBy: Este parámetro especifica el nombre del atributo en la entidad Vehiculos que mapea la relación inversa
(de "muchos" a "uno") con la entidad Persona. En este ejemplo, la entidad persona tiene un campo pedido que se mapea
con la relación a Persona, por lo que se usa "Persona" como valor de mappedBy.

- cascade: Este parámetro indica cómo se deben propagar las operaciones de persistencia en cascada de la entidad padre 
a la entidad hija. En este caso, CascadeType.ALL indica que todas las operaciones de persistencia (persistir, actualizar,
eliminar) en Persona se deben propagar a las entidades Vehiculo asociadas.

- orphanRemoval: Este parámetro indica si las entidades hijas deben ser eliminadas si se eliminan de la colección de la
entidad padre. En este caso, true indica que las entidades Vehiculos que se eliminan de la lista de VehiculosLista en un 
objeto Persona también deben ser eliminadas de la base de datos.

- optional: Este parámetro indica si la relación es opcional o no. Si se establece en false, se requiere que exista al
menos una entidad "muchos" para cada entidad "uno". Si se establece en true, se permite que la entidad "uno" no tenga 
entidades "muchos" asociadas.


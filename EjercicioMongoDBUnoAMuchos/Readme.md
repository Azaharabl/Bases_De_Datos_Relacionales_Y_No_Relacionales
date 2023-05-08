# Mongo DB Asincrono
- Ejercicio donde tendremos una relación 1 - 1
- MongoDB
- Forma asincrona con corrutinas

### Pasos
1. Dependencias de corrutinas y de mongo db asincrono
2. hacemos una imagen de doker de mongo : 
    - docker pull mongo
    - docker run -d -p 27017:27017 --name mongodb mongo
2. Creamos un DBmanager de mongo ( DBManager )
   - Creamos coleciones (una para cada clase)
   - Iniciamos la Sesion
   - Iniciamos la bbdd
3. Realizamos nuestras clases
4. Realizamos un maper
   - Este nos ayudará a pasar de Document, que es lo que usa mongo a pasar a nuestro modelo
5. Realizamos el repositorio
   - tenemos que tener en cuaenta que usaremos los mapers
6. Relaciones:
   - Mongo es un lenguage noSQL o no relacional.
   - Pero nosotros podemos manejar las relaciones a nustro gusto
   - Tambien podemos hacer objetos endevidos
   - Para ayudarnos vamos a crear la clase ManagerController que nos va a ayudar a manehar de un modo mas eficiente los dos controller de la bbdd.
















https://www.baeldung.com/java-mongodb
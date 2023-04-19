package repository


interface CrudRepository<ID, T> {
    fun findAll(): List<T>
    fun dropById(id: ID): Boolean
    fun findById(id: ID): T?
    fun findByUuid(uuid: String): T?
    fun updateByUuid(car: T): Boolean
    fun exixstsById(id: ID): Boolean
    fun create(car: T): Int
}
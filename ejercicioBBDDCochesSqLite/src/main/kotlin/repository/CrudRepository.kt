package repository

import model.CarDto

interface CrudRepository<ID, T> {
    fun findAll(): List<T>
    fun dropById(id: ID): Boolean
    fun findById(id: ID): T?
    fun findByUuid(uuid: String): T?
    fun updateByUuid(car: CarDto): Boolean
    fun exixstsById(id: ID): Boolean
    fun create(car: CarDto): Int
}
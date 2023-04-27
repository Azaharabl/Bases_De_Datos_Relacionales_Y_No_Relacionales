package repository.InterfacesReposiitory

import entity.Persona

interface RepositoryInterface <ID , T> {
    fun create(t : T): T
    fun update(t: T):Boolean
    fun delete(t:T):Boolean
    fun getById(id: ID):T?
    fun getAll():List<T>
}
package controllers

import entyties.Persona
import maper.toDocument
import maper.toPersona
import org.bson.Document

interface CrudInterface<ID,  T> {
    fun create(p : T)
    fun findByUuid(uuid:ID): T?
    fun update(p: T)
    fun delete(uuid:ID)
    fun findAll(): List<T>
    fun deleteAll()
}
package controllers

import controllers.CrudInterface
import entyties.Persona
import entyties.Vehiculo

interface VehiculosInterface : CrudInterface<String,Vehiculo>{

    fun findAllByPersonaId(personaId: String): List<Vehiculo>
}
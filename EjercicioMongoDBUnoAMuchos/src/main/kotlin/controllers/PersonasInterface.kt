package controllers

import controllers.CrudInterface
import entyties.Persona

interface PersonasInterface : CrudInterface<String,Persona>{
}
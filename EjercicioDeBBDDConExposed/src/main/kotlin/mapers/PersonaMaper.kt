package mapers

import entitys.PersonaDao
import org.jetbrains.exposed.dao.id.EntityID
import poko.Persona

fun PersonaDao.toPersona(): Persona{
   return  Persona(
       personaId = this.id.value,
       nombre = this.nombre,
       fechaCarnet = this.fechaCarnet
   )}


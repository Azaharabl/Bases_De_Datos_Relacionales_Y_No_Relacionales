package entity


import org.hibernate.engine.spi.CascadeStyles.ALL
import java.time.LocalDate

import javax.persistence.*

@Entity
@NamedQuery(name = "Persona.findAll", query = "SELECT t FROM Persona t")
class Persona (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var nombre : String = "",
    var fechaCarnet : LocalDate = LocalDate.now(),

    @OneToMany(mappedBy = "persona", orphanRemoval = false)
    var listaVehiculos : List<Vehiculo> = listOf()
){
    override fun toString(): String {
        return "Persona(id=$id, nombre='$nombre', fechaCarnet=$fechaCarnet)"
    }
}
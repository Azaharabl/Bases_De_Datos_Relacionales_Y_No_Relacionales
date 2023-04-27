package entity


import java.time.LocalDate
import javax.persistence.*

@Entity
@NamedQuery(name = "Vehiculo.findAll", query = "SELECT t FROM Vehiculo t")
class Vehiculo (
    @Id
    var uuid: String = "",
    var mark : String= "",
    var model : String= "",
    var date : LocalDate = LocalDate.now(),
    var engine : String= "",
    var createAt : LocalDate = LocalDate.now(),
    var updateAt : LocalDate = LocalDate.now(),
    var deleted: Boolean = false,
    @ManyToOne(optional = true)
    @JoinColumn(name = "persona", nullable = true )
    var persona : Persona ? = null

){
    override fun toString(): String {
        return "Vehiculo(uuid='$uuid', mark='$mark', model='$model', date=$date)"
    }
}
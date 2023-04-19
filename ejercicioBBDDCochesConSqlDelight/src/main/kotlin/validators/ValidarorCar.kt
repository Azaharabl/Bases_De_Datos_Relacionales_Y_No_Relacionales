package validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import errors.CarErrors
import model.Car
import com.github.michaelbull.result.Result
import database.CarDto


fun CarDto.ValidarorCar (): Result<CarDto, CarErrors> {

        if(uuid == null){ return Err(CarErrors.carNotValid("el uuid no es Nulo"))
        }else if (uuid.isBlank()){
            return Err(CarErrors.carNotValid("el uuid no es valido"))
        }else if (mark == null){
            return Err(CarErrors.carNotValid("el marck es nulo"))
        }else if(mark.isBlank()){
            return Err(CarErrors.carNotValid("el uuid no es valido"))
        }else if (model == null){
            return Err(CarErrors.carNotValid("el modelo es nulo"))
        }else if(model!!.isBlank()){
            return Err(CarErrors.carNotValid("el modelo no es valido"))
        }else if (date == null){
            return Err(CarErrors.carNotValid("el nombre es nulo"))
        }else if(date.isBlank()){
            return Err(CarErrors.carNotValid("el uuid no es valido"))
        }else if (engine == null){
            return Err(CarErrors.carNotValid("el engine es nulo"))
        }else if(engine!!.isBlank()){
            return Err(CarErrors.carNotValid("el engine no es valido"))
        }else if (createAt == null){
            return Err(CarErrors.carNotValid("el creacion es nulo"))
        }else if(createAt.isBlank()){
            return Err(CarErrors.carNotValid("el creacion no es valido"))
        }else if(updateAt == null){
            return Err(CarErrors.carNotValid("el actualizacion es nulo"))
        } else if(updateAt.isBlank()){
            return Err(CarErrors.carNotValid("el actualizacion no es valido"))
        }else if (deleted == null){
            return Err(CarErrors.carNotValid("el eliminado es nulo"))
        }else if(deleted!!.isBlank()){
            return Err(CarErrors.carNotValid("el eliminado no es valido"))
        }
    return Ok(this)




}

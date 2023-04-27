package validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import errors.CarErrors
import model.Car
import com.github.michaelbull.result.Result


fun Car.ValidarorCar (): Result<Car, CarErrors> {
    return when {
        uuid.isBlank() -> Err(CarErrors.carNotValid("el uuid no es valido"))
        mark.isBlank() -> Err(CarErrors.carNotValid("la maraca no es valido"))
        model.isBlank() -> Err(CarErrors.carNotValid("el model no es valido"))
        else -> {
            Ok(this)
        }
    }

}

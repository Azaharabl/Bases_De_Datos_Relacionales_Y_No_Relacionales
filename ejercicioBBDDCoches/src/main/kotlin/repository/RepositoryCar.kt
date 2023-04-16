package repository

import model.CarDto

interface RepositoryCar : CrudRepository<Long,CarDto >  {


}
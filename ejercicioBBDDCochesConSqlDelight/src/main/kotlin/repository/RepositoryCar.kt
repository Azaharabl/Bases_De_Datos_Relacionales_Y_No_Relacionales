package repository

import database.CarDto


interface RepositoryCar : CrudRepository<Long, CarDto>  {


}
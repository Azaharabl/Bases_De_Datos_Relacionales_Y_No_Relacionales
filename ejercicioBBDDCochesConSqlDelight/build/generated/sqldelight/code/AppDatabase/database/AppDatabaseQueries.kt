package database

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> findAll(mapper: (
    id: Long,
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String
  ) -> T): Query<T>

  public fun findAll(): Query<CarDto>

  public fun <T : Any> findById(id: Long, mapper: (
    id: Long,
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String
  ) -> T): Query<T>

  public fun findById(id: Long): Query<CarDto>

  public fun <T : Any> findByUuid(uuid: String, mapper: (
    id: Long,
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String
  ) -> T): Query<T>

  public fun findByUuid(uuid: String): Query<CarDto>

  public fun <T : Any> exixstsById(id: Long, mapper: (
    id: Long,
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String
  ) -> T): Query<T>

  public fun exixstsById(id: Long): Query<CarDto>

  public fun dropById(id: Long): Unit

  public fun updateById(
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String,
    id: Long
  ): Unit

  public fun create(
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String
  ): Unit
}

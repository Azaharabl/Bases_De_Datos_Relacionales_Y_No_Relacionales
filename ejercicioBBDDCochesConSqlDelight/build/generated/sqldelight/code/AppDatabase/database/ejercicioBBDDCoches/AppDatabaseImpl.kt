package database.ejercicioBBDDCoches

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import database.AppDatabase
import database.AppDatabaseQueries
import database.CarDto
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS carDto (
          |                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,
          |                uuid TEXT UNIQUE NOT NULL,
          |                mark TEXT NOT NULL,
          |                model TEXT NOT NULL,
          |                date TEXT NOT NULL,
          |                engine TEXT NOT NULL,
          |                createAt TEXT NOT NULL,
          |                updateAt TEXT NOT NULL,
          |                deleted TEXT NOT NULL
          |             )
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val findAll: MutableList<Query<*>> = copyOnWriteList()

  internal val findById: MutableList<Query<*>> = copyOnWriteList()

  internal val findByUuid: MutableList<Query<*>> = copyOnWriteList()

  internal val exixstsById: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> findAll(mapper: (
    id: Long,
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String
  ) -> T): Query<T> = Query(949119869, findAll, driver, "AppDatabase.sq", "findAll",
      "SELECT * FROM carDto") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getString(8)!!
    )
  }

  public override fun findAll(): Query<CarDto> = findAll { id, uuid, mark, model, date, engine,
      createAt, updateAt, deleted ->
    CarDto(
      id,
      uuid,
      mark,
      model,
      date,
      engine,
      createAt,
      updateAt,
      deleted
    )
  }

  public override fun <T : Any> findById(id: Long, mapper: (
    id: Long,
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String
  ) -> T): Query<T> = FindByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getString(8)!!
    )
  }

  public override fun findById(id: Long): Query<CarDto> = findById(id) { id_, uuid, mark, model,
      date, engine, createAt, updateAt, deleted ->
    CarDto(
      id_,
      uuid,
      mark,
      model,
      date,
      engine,
      createAt,
      updateAt,
      deleted
    )
  }

  public override fun <T : Any> findByUuid(uuid: String, mapper: (
    id: Long,
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String
  ) -> T): Query<T> = FindByUuidQuery(uuid) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getString(8)!!
    )
  }

  public override fun findByUuid(uuid: String): Query<CarDto> = findByUuid(uuid) { id, uuid_, mark,
      model, date, engine, createAt, updateAt, deleted ->
    CarDto(
      id,
      uuid_,
      mark,
      model,
      date,
      engine,
      createAt,
      updateAt,
      deleted
    )
  }

  public override fun <T : Any> exixstsById(id: Long, mapper: (
    id: Long,
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String
  ) -> T): Query<T> = ExixstsByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getString(6)!!,
      cursor.getString(7)!!,
      cursor.getString(8)!!
    )
  }

  public override fun exixstsById(id: Long): Query<CarDto> = exixstsById(id) { id_, uuid, mark,
      model, date, engine, createAt, updateAt, deleted ->
    CarDto(
      id_,
      uuid,
      mark,
      model,
      date,
      engine,
      createAt,
      updateAt,
      deleted
    )
  }

  public override fun dropById(id: Long): Unit {
    driver.execute(-395357268, """DELETE FROM carDto WHERE id = ?""", 1) {
      bindLong(1, id)
    }
    notifyQueries(-395357268, {database.appDatabaseQueries.findById +
        database.appDatabaseQueries.exixstsById + database.appDatabaseQueries.findAll +
        database.appDatabaseQueries.findByUuid})
  }

  public override fun updateById(
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String,
    id: Long
  ): Unit {
    driver.execute(1582037446,
        """UPDATE carDto SET uuid =?, mark =?, model =?, date =?, engine =? , createAt =?, updateAt =?, deleted =? WHERE id =?""",
        9) {
      bindString(1, uuid)
      bindString(2, mark)
      bindString(3, model)
      bindString(4, date)
      bindString(5, engine)
      bindString(6, createAt)
      bindString(7, updateAt)
      bindString(8, deleted)
      bindLong(9, id)
    }
    notifyQueries(1582037446, {database.appDatabaseQueries.findById +
        database.appDatabaseQueries.exixstsById + database.appDatabaseQueries.findAll +
        database.appDatabaseQueries.findByUuid})
  }

  public override fun create(
    uuid: String,
    mark: String,
    model: String,
    date: String,
    engine: String,
    createAt: String,
    updateAt: String,
    deleted: String
  ): Unit {
    driver.execute(506960903,
        """INSERT INTO carDto (uuid, mark, model, date, engine, createAt,updateAt,deleted) VALUES(?,?,?,?,?,?,?,?)""",
        8) {
      bindString(1, uuid)
      bindString(2, mark)
      bindString(3, model)
      bindString(4, date)
      bindString(5, engine)
      bindString(6, createAt)
      bindString(7, updateAt)
      bindString(8, deleted)
    }
    notifyQueries(506960903, {database.appDatabaseQueries.findById +
        database.appDatabaseQueries.exixstsById + database.appDatabaseQueries.findAll +
        database.appDatabaseQueries.findByUuid})
  }

  private inner class FindByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(findById, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-642013834,
        """SELECT * FROM carDto WHERE id = ?""", 1) {
      bindLong(1, id)
    }

    public override fun toString(): String = "AppDatabase.sq:findById"
  }

  private inner class FindByUuidQuery<out T : Any>(
    public val uuid: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(findByUuid, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(1500373334,
        """SELECT * FROM carDto WHERE uuid = ?""", 1) {
      bindString(1, uuid)
    }

    public override fun toString(): String = "AppDatabase.sq:findByUuid"
  }

  private inner class ExixstsByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(exixstsById, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-518949481,
        """SELECT * FROM carDto WHERE id = ?""", 1) {
      bindLong(1, id)
    }

    public override fun toString(): String = "AppDatabase.sq:exixstsById"
  }
}

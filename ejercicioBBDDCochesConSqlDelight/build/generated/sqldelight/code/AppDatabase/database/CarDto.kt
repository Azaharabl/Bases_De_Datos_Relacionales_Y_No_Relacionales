package database

import kotlin.Long
import kotlin.String

public data class CarDto(
  public val id: Long,
  public val uuid: String,
  public val mark: String,
  public val model: String,
  public val date: String,
  public val engine: String,
  public val createAt: String,
  public val updateAt: String,
  public val deleted: String
) {
  public override fun toString(): String = """
  |CarDto [
  |  id: $id
  |  uuid: $uuid
  |  mark: $mark
  |  model: $model
  |  date: $date
  |  engine: $engine
  |  createAt: $createAt
  |  updateAt: $updateAt
  |  deleted: $deleted
  |]
  """.trimMargin()
}

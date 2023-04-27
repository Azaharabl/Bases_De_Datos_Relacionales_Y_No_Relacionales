package hibernate

import java.io.Closeable
import java.sql.SQLException
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Persistence

object HivernateManager : Closeable {
    private var entityManagerFactory = Persistence.createEntityManagerFactory("AzaharaBBDD")
    var manager: EntityManager = entityManagerFactory.createEntityManager()
    private var transaction: EntityTransaction  = manager.transaction


    fun open() {
        manager = entityManagerFactory.createEntityManager()
        transaction = manager.transaction
    }

    override fun close() {
        manager.close()
    }

    //hacer transaciones
    fun query(operations: () -> Unit) {
        open()
        try {
            operations()
        } catch (e: SQLException) {
           e.printStackTrace()
        } finally {
            close()
        }
    }


    fun transaction(operations: () -> Unit) {
        open()
        try {
            transaction.begin()
            operations()
            transaction.commit()
        } catch (e: SQLException) {
            transaction.rollback()
            throw SQLException(e)
        } finally {
            close()
        }
    }
}

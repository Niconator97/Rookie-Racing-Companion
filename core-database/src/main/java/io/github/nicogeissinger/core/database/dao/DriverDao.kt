package io.github.nicogeissinger.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.github.nicogeissinger.core.database.entity.DriverEntity

@Dao
interface DriverDao {

    @Query(
        """
            SELECT COUNT(*) FROM drivers
        """
    )
    fun countDrivers(): Int

    @Upsert
    suspend fun upsert(driver: DriverEntity)

    @Upsert
    suspend fun upsertAll(drivers: List<DriverEntity>)
}
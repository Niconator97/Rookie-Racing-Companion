package io.github.nicogeissinger.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.github.nicogeissinger.core.database.entity.RaceClassEntity

@Dao
interface RaceClassDao {

    @Upsert
    suspend fun upsertAll(classes: List<RaceClassEntity>)

    @Query("SELECT COUNT(*) FROM race_class")
    suspend fun countClasses(): Long
}
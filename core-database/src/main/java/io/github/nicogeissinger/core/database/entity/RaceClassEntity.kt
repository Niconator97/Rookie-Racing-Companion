package io.github.nicogeissinger.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "race_class")
data class RaceClassEntity(
    @PrimaryKey val id: String,
    val displayName: String
)
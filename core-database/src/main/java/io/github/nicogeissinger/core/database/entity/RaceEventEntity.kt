package io.github.nicogeissinger.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "race_events",
    foreignKeys = [
        ForeignKey(
            entity = RaceClassEntity::class,
            parentColumns = ["id"],
            childColumns = ["classId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("classId"),
        Index(value = ["classId", "startTimeUtcMillis"]),
        Index(value = ["classId", "endTimeUtcMillis"])
    ]
)
data class RaceEventEntity(
    @PrimaryKey val eventId: String,
    val classId: String,
    val title: String,
    val trackName: String,
    val startTimeUtcMillis: Long,
    val endTimeUtcMillis: Long
)
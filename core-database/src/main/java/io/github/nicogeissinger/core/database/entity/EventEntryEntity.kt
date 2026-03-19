package io.github.nicogeissinger.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "event_entries",
     primaryKeys = [
         "driverId",
         "eventId"
     ],

    foreignKeys = [
        ForeignKey(
            entity = DriverEntity::class,
            parentColumns = ["id"],
            childColumns = ["driverId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RaceEventEntity::class,
            parentColumns = ["eventId"],
            childColumns = ["eventId"],
            onDelete = ForeignKey.CASCADE
        )
    ],

    indices = [
        Index("driverId"),
        Index("eventId")
    ]
)
data class EventEntryEntity(
    val driverId: String,

    val eventId: String,

    val isRegistered: Boolean,

    val updatedAtUtcMillis: Long
)
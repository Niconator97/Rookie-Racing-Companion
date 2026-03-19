package io.github.nicogeissinger.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "driver_standing",

    primaryKeys = [
        "classId",
        "driverId"
    ],

    foreignKeys = [
        ForeignKey(
            entity = RaceClassEntity::class,
            parentColumns = ["id"],
            childColumns = ["classId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DriverEntity::class,
            parentColumns = ["id"],
            childColumns = ["driverId"],
            onDelete = ForeignKey.CASCADE
        )
    ],

    indices = [
        Index(value = ["classId"]),
        Index(value = ["classId", "rank"]),
        Index(value = ["classId", "points"])
    ]
)
data class DriverStandingsEntity(
    val classId: String,

    val driverId: String,

    val rank: Int,

    val points: Int,

    val updatedAtUtcMillis: Long
)
package io.github.nicogeissinger.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "driver_class_cross",

    primaryKeys = [
        "driverId",
        "classId"
    ],

    foreignKeys = [
        ForeignKey(
            entity = DriverEntity::class,
            parentColumns = ["id"],
            childColumns = ["driverId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RaceClassEntity::class,
            parentColumns = ["id"],
            childColumns = ["classId"],
            onDelete = ForeignKey.CASCADE
        )
    ],

    indices = [
        Index("driverId"),
        Index("classId")
    ]
)
data class DriverClassCrossRefEntity(
    val driverId: String,

    val classId: String
)
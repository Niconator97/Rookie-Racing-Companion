package io.github.nicogeissinger.core.database.dao.model

import androidx.room.Embedded
import io.github.nicogeissinger.core.database.entity.RaceEventEntity

data class RaceEventParticipantCount(
    @Embedded val event: RaceEventEntity,
    val participantCount: Int
)
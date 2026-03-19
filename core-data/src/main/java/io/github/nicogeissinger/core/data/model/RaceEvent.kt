package io.github.nicogeissinger.core.data.model

import io.github.nicogeissinger.core.database.dao.model.RaceEventParticipantCount

data class RaceEvent (
    val eventId: String,
    val classId: String,
    val title: String,
    val trackName: String,
    val startTimeUtcMillis: Long,
    val endTimeUtcMillis: Long,
    val participantCount: Int
)
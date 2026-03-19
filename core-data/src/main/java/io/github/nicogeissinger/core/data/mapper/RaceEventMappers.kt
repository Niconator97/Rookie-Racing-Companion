package io.github.nicogeissinger.core.data.mapper

import io.github.nicogeissinger.core.data.model.RaceEvent
import io.github.nicogeissinger.core.database.dao.model.RaceEventParticipantCount
import io.github.nicogeissinger.core.database.entity.RaceEventEntity

fun RaceEventParticipantCount.toRaceEvent(): RaceEvent {
    return RaceEvent(
        eventId = event.eventId,
        classId = event.classId,
        title = event.title,
        trackName = event.trackName,
        startTimeUtcMillis = event.startTimeUtcMillis,
        endTimeUtcMillis = event.endTimeUtcMillis,
        participantCount = participantCount
    )
}
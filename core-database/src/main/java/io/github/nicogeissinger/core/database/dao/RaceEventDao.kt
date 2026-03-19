package io.github.nicogeissinger.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.github.nicogeissinger.core.database.dao.model.RaceEventParticipantCount
import io.github.nicogeissinger.core.database.entity.RaceEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RaceEventDao {
    @Query(
        """
        SELECT e.*,
               COUNT(p.driverId) AS participantCount
        FROM race_events e
        LEFT JOIN event_entries p ON p.eventId = e.eventId
        WHERE (:classId IS NULL OR e.classId = :classId)
        GROUP BY e.eventId
        ORDER BY e.startTimeUtcMillis ASC
    """
    )
    fun observeEventsWithParticipantCount(
        classId: String? = null
    ): Flow<List<RaceEventParticipantCount>>

    @Query(
        """
        SELECT e.*,
               COUNT(p.driverId) AS participantCount
        FROM race_events e
        LEFT JOIN event_entries p ON p.eventId = e.eventId
        WHERE e.startTimeUtcMillis >= :nowUtcMillis
          AND (:classId IS NULL OR e.classId = :classId)
        GROUP BY e.eventId
        ORDER BY e.startTimeUtcMillis ASC
        LIMIT 1
    """
    )
    fun observeNextEventWithParticipantCount(
        nowUtcMillis: Long,
        classId: String? = null
    ): Flow<RaceEventParticipantCount?>

    @Query(
        """
            SELECT COUNT(*) FROM race_events
        """
    )
    fun countEvents(): Long

    @Upsert
    suspend fun upsert(event: RaceEventEntity)

    @Upsert
    suspend fun upsertAll(events: List<RaceEventEntity>)
}
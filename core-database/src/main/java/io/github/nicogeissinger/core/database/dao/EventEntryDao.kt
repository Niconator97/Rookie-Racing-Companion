package io.github.nicogeissinger.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.github.nicogeissinger.core.database.entity.EventEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventEntryDao {

    // Participantcount
    @Query("SELECT COUNT(*) FROM event_entries WHERE eventId = :eventId")
    fun observeParticipantCount(eventId: String): Flow<Int>

    // List of Participants
    @Query("""
        SELECT driverId
        FROM event_entries
        WHERE eventId = :eventId
        ORDER BY driverId ASC
    """)
    fun observeParticipantDriverIds(eventId: String): Flow<List<String>>

    // Toggle / Set Registered (wenn isRegistered im Entry)
    @Query("""
        UPDATE event_entries
        SET isRegistered = :isRegistered,
            updatedAtUtcMillis = :updatedAtUtcMillis
        WHERE eventId = :eventId AND driverId = :driverId
    """)
    suspend fun setRegistered(
        eventId: String,
        driverId: String,
        isRegistered: Boolean,
        updatedAtUtcMillis: Long
    )

    @Upsert
    suspend fun upsert(entry: EventEntryEntity)

    @Upsert
    suspend fun upsertAll(entries: List<EventEntryEntity>)
}
package io.github.nicogeissinger.core.database.seed

import io.github.nicogeissinger.core.database.entity.DriverEntity
import io.github.nicogeissinger.core.database.entity.EventEntryEntity
import io.github.nicogeissinger.core.database.entity.RaceClassEntity
import io.github.nicogeissinger.core.database.entity.RaceEventEntity
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.Instant
import javax.inject.Inject

class SeedDataFactory @Inject constructor() {

    fun createEvents(nowUtc: Instant): List<RaceEventEntity> {
        val classId = "RTM"

        val baseTime: LocalDateTime = LocalDateTime.ofInstant(nowUtc, ZoneOffset.UTC)

        fun utcMillis(dt: LocalDateTime): Long = dt.toInstant(ZoneOffset.UTC).toEpochMilli()

        fun event(
            id: String,
            title: String,
            track: String,
            start: LocalDateTime,
            durationHours: Long = 2
        ) = RaceEventEntity(
            eventId = id,
            classId = classId,
            title = title,
            trackName = track,
            startTimeUtcMillis = utcMillis(start),
            endTimeUtcMillis = utcMillis(start.plusHours(durationHours))
        )

         return listOf(
             event(
                 id = "rtm_e1",
                 title = "RTM Round 1",
                 track = "Monza",
                 start = baseTime.plusDays(3).withHour(18).withMinute(30)
             ),
             event(
                 id = "rtm_e2",
                 title = "RTM Round 2",
                 track = "Nürburgring GP",
                 start = baseTime.plusDays(10).withHour(18).withMinute(30)
             ),
             event(
                 id = "rtm_e3",
                 title = "RTM Round 3",
                 track = "Silverstone GP",
                 start = baseTime.plusDays(17).withHour(18).withMinute(30)
             )
        )
    }

    fun createEntries(): List<EventEntryEntity> {
        val updateTime = Instant.now().toEpochMilli()

        return buildList {
            repeat(12) { i -> add(EventEntryEntity(driverId = "d${i + 1}", eventId = "rtm_e1", true, updatedAtUtcMillis = updateTime))}
            repeat(9) { i -> add(EventEntryEntity(driverId = "d${i + 1}", eventId = "rtm_e2", true, updatedAtUtcMillis = updateTime))}
            repeat(15) { i -> add(EventEntryEntity(driverId = "d${i + 1}", eventId = "rtm_e3", true, updatedAtUtcMillis = updateTime))}
        }
    }

    fun createDrivers(): List<DriverEntity> {
        return buildList {
            repeat(15) { i ->
                val id = "d${i + 1}"
                add(
                    DriverEntity(
                        id = id,
                        userName = "Driver$id",
                        realName = "Test Fahrer $id"
                    )
                )
            }
        }
    }

    fun createClasses(): List<RaceClassEntity> {
        val updateTime = Instant.now().toEpochMilli()

        return listOf(
            RaceClassEntity("RTM", "RTM"),
            RaceClassEntity("GT7", "GT7")
        )
    }
}
package io.github.nicogeissinger.rrlcompanion.feature.calendar.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.nicogeissinger.rrlcompanion.feature.calendar.presentation.CalendarScreen

const val CALENDAR_ROUTE = "calendar"

fun NavGraphBuilder.calendarGraph() {
    composable(route = CALENDAR_ROUTE) {
        CalendarScreen()
    }
}
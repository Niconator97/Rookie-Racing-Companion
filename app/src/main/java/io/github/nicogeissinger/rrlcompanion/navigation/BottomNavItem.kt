package io.github.nicogeissinger.rrlcompanion.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import io.github.nicogeissinger.rrlcompanion.feature.calendar.presentation.navigation.CALENDAR_ROUTE
import io.github.nicogeissinger.rrlcompanion.feature.dashboard.presentation.navigation.DASHBOARD_ROUTE
import io.github.nicogeissinger.rrlcompanion.feature.standings.presentation.navigation.STANDINGS_ROUTE

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Dashboard : BottomNavItem(
        route = DASHBOARD_ROUTE,
        label = "Dashboard",
        icon = Icons.Default.Home
    )

    object Calendar : BottomNavItem(
        route = CALENDAR_ROUTE,
        label = "Calendar",
        icon = Icons.Default.DateRange
    )

    object Standings : BottomNavItem(
        route = STANDINGS_ROUTE,
        label = "Standings",
        icon = Icons.Default.List
    )
}

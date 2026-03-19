package io.github.nicogeissinger.rrlcompanion.feature.dashboard.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.nicogeissinger.rrlcompanion.feature.dashboard.presentation.DashboardScreen

const val DASHBOARD_ROUTE = "dashboard"

fun NavGraphBuilder.dashboardGraph() {
    composable(route = DASHBOARD_ROUTE) {
        DashboardScreen()
    }
}

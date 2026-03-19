package io.github.nicogeissinger.rrlcompanion.feature.standings.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.nicogeissinger.rrlcompanion.feature.standings.presentation.StandingsScreen

const val STANDINGS_ROUTE = "standings"

fun NavGraphBuilder.standingsGraph() {
    composable(route = STANDINGS_ROUTE) {
        StandingsScreen()
    }
}
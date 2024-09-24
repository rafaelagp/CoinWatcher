package net.rafgpereira.coinwatcher.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {

    @Serializable
    data object MainScreen: Route()

    @Serializable
    data object AddToWatchlistScreen: Route()
}
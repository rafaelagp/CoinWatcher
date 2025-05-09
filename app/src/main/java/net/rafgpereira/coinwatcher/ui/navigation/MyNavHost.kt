package net.rafgpereira.coinwatcher.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.rafgpereira.coinwatcher.ui.screen.main.MainScreen

@Composable
fun MyNavHost(navController: NavHostController) =
    NavHost(
        navController = navController,
        startDestination = Route.MainScreen
    ) {
        composable<Route.MainScreen> {
            MainScreen(
                modifier = Modifier,
            )
        }
    }
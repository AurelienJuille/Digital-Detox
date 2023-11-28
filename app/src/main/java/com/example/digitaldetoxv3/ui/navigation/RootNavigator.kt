package com.example.digitaldetoxv3.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.digitaldetoxv3.ui.screens.ConfigureBlockScreen

@Composable
fun RootNavigator () {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "home",
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }) {
        composable("home") { BottomTabNavigator(navController) }
        composable("configureBlockScreen") { ConfigureBlockScreen() }
    }
}
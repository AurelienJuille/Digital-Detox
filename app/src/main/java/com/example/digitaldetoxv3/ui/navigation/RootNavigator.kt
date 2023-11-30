package com.example.digitaldetoxv3.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.digitaldetoxv3.ui.screens.BlockProgramFormScreen

@Composable
fun RootNavigator () {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "home",
        modifier = Modifier.fillMaxSize(),
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }) {
        composable("home") { BottomTabNavigator(navController) }
        composable("configureBlockScreen") { BlockProgramFormScreen(navController, modifier = Modifier) }
    }
}
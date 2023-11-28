package com.example.digitaldetoxv3.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.digitaldetoxv3.ui.screens.Block
import com.example.digitaldetoxv3.ui.screens.More
import com.example.digitaldetoxv3.ui.screens.Progress
import com.example.digitaldetoxv3.ui.screens.Screen
import com.example.digitaldetoxv3.ui.screens.Settings

@Composable
fun BottomTabNavigator (rootNavController: NavController) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Settings,
        Screen.Block,
        Screen.More,
        Screen.Progress
    )
    Scaffold(
    bottomBar = {
        BottomNavigation (
            modifier = Modifier
                .height(70.dp)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                BottomNavigationItem(
                    modifier = Modifier
                        .offset(y = (if (isSelected) -5 else 0).dp),
                    icon = { Icon(screen.icon, contentDescription = null, modifier = Modifier.size(if (isSelected) 30.dp else 20.dp)) },
                    label = { if (isSelected) {Text(stringResource(screen.resourceId), softWrap = false)}},
                    selected = isSelected,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
    ) { innerPadding ->
        NavHost(navController,
            startDestination = Screen.Block.route,
            Modifier.padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            composable(Screen.Block.route) { Block(navController, rootNavController) }
            composable(Screen.More.route) { More(navController, rootNavController) }
            composable(Screen.Progress.route) { Progress(navController, rootNavController) }
            composable(Screen.Settings.route) { Settings(navController, rootNavController) }
        }
    }
}
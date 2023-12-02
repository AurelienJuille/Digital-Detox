package com.example.digitaldetoxv3.ui.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.digitaldetoxv3.ui.screens.BlockProgramFormScreen
import com.example.digitaldetoxv3.viewmodels.BlockProgramFormViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigator () {
    val context = LocalContext.current
    val blockProgramFormViewModel = viewModel<BlockProgramFormViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BlockProgramFormViewModel(
                    context
                ) as T
            }
        }
    )
//    val blockProgramFormViewModel = BlockProgramFormViewModel(context = LocalContext.current)
    val packagesFlow = MutableStateFlow<List<Map<String, String>>>(emptyList()) // Initialisation avec une liste vide

    val scope = rememberCoroutineScope()

    scope.launch {
        blockProgramFormViewModel.appsListString.collect { appsListString ->
            val gson = Gson()
            val packages = if (!appsListString.isNullOrBlank()) {
                val packagesType = object : TypeToken<List<Map<String, String>>>() {}.type
                gson.fromJson<List<Map<String, String>>>(appsListString, packagesType)
            } else {
                emptyList()
            }

            packagesFlow.emit(packages)
        }
        packagesFlow.emit(emptyList())
    }

    val appsInfo: List<Map<String, String>> by packagesFlow.collectAsState(initial = emptyList())
    blockProgramFormViewModel.setAppsList(appsInfo)


    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "home",
        modifier = Modifier.fillMaxSize(),
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }) {
        composable("home") { BottomTabNavigator(navController) }
        composable("configureBlockScreen") { BlockProgramFormScreen(navController, blockProgramFormViewModel,modifier = Modifier) }
    }
}
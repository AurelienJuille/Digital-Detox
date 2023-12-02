package com.example.digitaldetoxv3.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digitaldetoxv3.data.Storage
import com.example.digitaldetoxv3.ui.components.BottomRoundCornerButton
import com.example.digitaldetoxv3.ui.components.ProgramDescriptionCard
import kotlinx.coroutines.launch

@Composable
fun BlockScreen(bottomBarNavController: NavController, rootNavController: NavController) {
    val context: Context = LocalContext.current
    val storage = Storage(context)
    val programs by storage.programsFlow.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp, top = 25.dp, start = 25.dp, end = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                programs.forEach { program ->
                    ProgramDescriptionCard(blockProgram = program)
                }
            }
        }
        BottomRoundCornerButton(
            onclick = {
//                scope.launch { storage.updateAppsList() }

                rootNavController.navigate("configureBlockScreen")
            },
            text = "Ajouter",
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

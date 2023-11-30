package com.example.digitaldetoxv3.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Block(bottomBarNavController: NavController,
          rootNavController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Box(modifier = Modifier
            .verticalScroll(rememberScrollState())) {
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp, top = 25.dp, start = 25.dp, end = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "test")
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            onClick = {
                rootNavController.navigate("configureBlockScreen")},
        ) {
            Icon(imageVector = Icons.Default.AddCircle, "Add Program")
        }
    }
}
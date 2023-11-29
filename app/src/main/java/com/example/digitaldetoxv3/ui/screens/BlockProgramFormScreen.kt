package com.example.digitaldetoxv3.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun ConfigureBlockScreen (navController: NavController) {
    Box (modifier = Modifier.background(Color.Green)) {
        Button(onClick = { navController.navigate("home") }) {

        }
    }
}
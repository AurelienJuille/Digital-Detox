package com.example.digitaldetoxv3.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun Progress(navController: NavController, rootNavController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Blue)) {
        // Your composable content here
    }
}
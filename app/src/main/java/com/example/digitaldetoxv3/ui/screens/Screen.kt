package com.example.digitaldetoxv3.ui.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

import com.example.digitaldetoxv3.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Block : Screen("block", R.string.block, Icons.Default.Close)
    object More : Screen("more", R.string.more, Icons.Default.Add)
    object Progress : Screen("progress", R.string.progress, Icons.Default.ArrowForward)
    object Settings : Screen("settings", R.string.settings, Icons.Default.Settings)
}
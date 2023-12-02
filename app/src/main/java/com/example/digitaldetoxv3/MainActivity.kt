package com.example.digitaldetoxv3

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.example.digitaldetoxv3.data.Storage
import com.example.digitaldetoxv3.ui.navigation.RootNavigator
import com.example.digitaldetoxv3.ui.theme.DigitalDetoxV3Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            DigitalDetoxV3Theme {
                RootNavigator()
            }
        }
    }
}
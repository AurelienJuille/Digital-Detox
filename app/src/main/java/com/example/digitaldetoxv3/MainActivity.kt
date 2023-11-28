package com.example.digitaldetoxv3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.digitaldetoxv3.ui.navigation.RootNavigator
import com.example.digitaldetoxv3.ui.theme.DigitalDetoxV3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalDetoxV3Theme {
                RootNavigator()
            }
        }
    }
}
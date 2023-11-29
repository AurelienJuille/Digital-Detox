package com.example.digitaldetoxv3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.digitaldetoxv3.data.MyDatabase
import com.example.digitaldetoxv3.ui.navigation.RootNavigator
import com.example.digitaldetoxv3.ui.theme.DigitalDetoxV3Theme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "myDatabase.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalDetoxV3Theme {
                RootNavigator()
            }
        }
    }
}
package com.example.digitaldetoxv3.ui.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.digitaldetoxv3.data.BlockProgram
import com.example.digitaldetoxv3.data.StoreUserPrograms
import kotlinx.coroutines.launch
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlockProgramFormScreen (navController: NavController,
                            modifier: Modifier) {
    val name = "Programme 2"
    val packages = "Tik Tok"
    val startTime = LocalTime.now().toString()
    val endTime = "17:00"
    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userPrograms = StoreUserPrograms(context)
    val text = userPrograms.getPrograms.collectAsState(initial = "")

    val program = BlockProgram(name, packages, startTime, endTime)
    var lastProgram: BlockProgram

    Column {
        Button(onClick = {
            scope.launch() {
                userPrograms.addProgram(program, text.value!!)
                lastProgram = userPrograms.getProgramsFromString(text.value!!).last()
            }

        }) {
            Text(text = text.value!!)
        }
        Button(onClick = {
            scope.launch() {
                userPrograms.reset()
            }
        }) {
            Text(text = "RESET")
        }
    }
}


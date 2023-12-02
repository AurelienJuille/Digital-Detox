package com.example.digitaldetoxv3.ui.screens

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digitaldetoxv3.data.Storage
import com.example.digitaldetoxv3.ui.components.BottomRoundCornerButton
import com.example.digitaldetoxv3.viewmodels.BlockProgramFormViewModel
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.util.Calendar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlockProgramFormScreen (navController: NavController,
                            vm: BlockProgramFormViewModel,
                            modifier: Modifier) {
    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()


    var name by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Paramètres", modifier = Modifier.padding(top=25.dp))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }, modifier = Modifier.padding(top=25.dp)) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = AppBarDefaults.TopAppBarElevation,
                modifier = Modifier.height(85.dp)
            )
        },
        content = {
            Box(modifier = Modifier
                .fillMaxSize()) {
                Box(modifier = Modifier
                    .verticalScroll(rememberScrollState())) {
                    Column (modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 100.dp, top = 25.dp, start = 25.dp, end = 25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        // Name input field
                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                name = it
                                vm.setName(it)
                                            },
                            label = { Text("Name") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )

                        AppPackagesList(vm)

                        // Start time picker
                        TimePicker(vm, "From")

                        // End time picker
                        TimePicker(vm, "To")

                        BottomRoundCornerButton(onclick = {
                            scope.launch() {
                                vm.addBlockProgramToDataBase()
                            }
                            navController.navigate("home")
                                                          }, text = "Valider", modifier = Modifier)
                    }
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimePicker(vm: BlockProgramFormViewModel, label: String){
    // Fetching local context
    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    var isStartTime = false

    if (label == "From") {
        isStartTime = true
    }

    // Value for storing time as a string
    val mTime = remember {
        if (isStartTime) {
            mutableStateOf(LocalTime.MIN)
        } else {
            mutableStateOf((LocalTime.MAX))
        }
    }

    // Creating a TimePicker dialog
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            mTime.value = LocalTime.of(mHour, mMinute)

            if (isStartTime) {
                vm.setStartTime(LocalTime.of(mHour, mMinute))
            } else {
                vm.setEndTime(LocalTime.of(mHour, mMinute))
            }

        }, mHour, mMinute, true
    )

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // On button click, TimePicker is
        // displayed, user can select a time
        Button(onClick = { mTimePickerDialog.show() }) {
            Text(text = label + " ${mTime.value.hour.toString().padStart(2, '0')} : ${mTime.value.minute.toString().padStart(2, '0')}", color = Color.White)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AppPackagesList(vm: BlockProgramFormViewModel) {
    val appsInfo = vm.appsList

    var showAllApps by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        val appsToShow = if (showAllApps) {
            appsInfo
        } else {
            appsInfo.take(5) // Afficher seulement les 5 premiers éléments
        }

        appsToShow.forEach { appInfo ->
            val packageName = appInfo["package"] ?: ""
            val appName = appInfo["name"] ?: ""

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = vm.selectedPackages.contains(packageName),
                    onCheckedChange = { isChecked ->
                        vm.updateSelectedPackages(packageName, isChecked)
                    },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = appName)
            }
        }

        // Bouton pour afficher/cacher les autres éléments
        if (appsInfo.size > 5) {
            Button(
                onClick = { showAllApps = !showAllApps },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(if (showAllApps) "Cacher" else "Afficher plus")
            }
        }
    }
}
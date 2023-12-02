package com.example.digitaldetoxv3.viewmodels

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.datastore.dataStore
import com.example.digitaldetoxv3.data.BlockProgram
import com.example.digitaldetoxv3.data.Storage
import com.google.gson.Gson
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class BlockProgramFormViewModel (
    val context: Context
): ViewModel() {
    private val storage = Storage(context)

    val appsListString get() = storage.getAppsList

    var appsList = emptyList<Map<String, String>>()
        private set

    fun setAppsList(list: List<Map<String, String>>) {
        Log.d("TEST", "RE")
        appsList = list
    }

    var name = ""
        private set

    fun setName(str: String) {
        name = str
    }


    private val _selectedPackages = mutableStateListOf<String>()
    val selectedPackages: List<String> get() = _selectedPackages

    fun updateSelectedPackages(packageName: String, isChecked: Boolean) {
        if (isChecked) {
            _selectedPackages.add(packageName)
        } else {
            _selectedPackages.remove(packageName)
        }
    }

    var startTime = LocalTime.MIN
        private set

    fun setStartTime(time: LocalTime) {
        startTime = time
    }

    var endTime = LocalTime.MAX
        private set

    fun setEndTime(time: LocalTime) {
        endTime = time
    }

    suspend fun addBlockProgramToDataBase() {
        val formattedStartTime = String.format("%02d:%02d", startTime.hour, startTime.minute)
        val formattedEndTime = String.format("%02d:%02d", endTime.hour, endTime.minute)

        val program = BlockProgram(
            name = name,
            packages = _selectedPackages,
            startTime = formattedStartTime,
            endTime = formattedEndTime)
        Log.d("TEST", program.toString())
        storage.addProgram(program = program)
    }


}
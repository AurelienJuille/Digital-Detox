package com.example.digitaldetoxv3.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class StoreUserPrograms (private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserPrograms")
        val USER_PROGRAMS_KEY = stringPreferencesKey(("UserPrograms"))

    }

    val getPrograms: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_PROGRAMS_KEY] ?: ""
        }

    fun getProgramsFromString(jsonString: String): MutableList<BlockProgram> {
        val gson = Gson()
        val listType = object : TypeToken<MutableList<BlockProgram>>() {}.type
        return gson.fromJson(jsonString, listType)
    }

    private suspend fun setPrograms(programs: MutableList<BlockProgram>) {
        val gson = Gson()
        val jsonString = gson.toJson(programs).toString()

        Log.d("TEST", "$jsonString => TO PROGRAMS JSON STRING")

        context.dataStore.edit { preferences ->
            preferences[USER_PROGRAMS_KEY] = jsonString
        }
    }

    suspend fun addProgram(program: BlockProgram, str: String) {
        var programs: MutableList<BlockProgram> = mutableListOf<BlockProgram>()
        try {
            if (str.isNotEmpty()) {
                programs = getProgramsFromString(str)
            }
        } catch(err: Throwable) {
            reset()
        }
        programs.add(program)
        setPrograms(programs)


    }

    suspend fun changeTest() {
        context.dataStore.edit { preferences ->
            preferences[USER_PROGRAMS_KEY] = "SUCCESSFUL"
        }
    }

    suspend fun reset() {
        context.dataStore.edit { preferences ->
            preferences[USER_PROGRAMS_KEY] = ""
        }
    }
}
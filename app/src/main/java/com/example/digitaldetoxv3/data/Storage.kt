package com.example.digitaldetoxv3.data

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class Storage (private val context: Context) {
    private val gson = Gson()
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserPrograms")
        val USER_PROGRAMS_KEY = stringPreferencesKey(("UserPrograms"))
        val APPS_LIST_KEY = stringPreferencesKey(("AppsList"))

    }

    val getAppsList: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[APPS_LIST_KEY] ?: ""
        }

    suspend fun updateAppsList() {
        val appsList = mutableListOf<Map<String, String>>()

        // Créer un Intent pour filtrer les applications lancées depuis le lanceur
        val launcherIntent = Intent(Intent.ACTION_MAIN, null)
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        // Obtenir la liste des ResolveInfo correspondant à cet Intent
        val packageManager: PackageManager = context.packageManager
        val resolveInfos: List<ResolveInfo> = packageManager.queryIntentActivities(launcherIntent, 0)

        // Parcourir les ResolveInfo pour obtenir les noms et les packages des applications
        for (resolveInfo in resolveInfos) {
            val appName = resolveInfo.loadLabel(packageManager).toString()
            val packageName = resolveInfo.activityInfo.packageName

            val appMap = mapOf("name" to appName, "package" to packageName)
            appsList.add(appMap)
        }

        // Trier la liste des applications par ordre alphabétique du nom
        val sortedAppsList = appsList.sortedBy { it["name"] }

        // Convertir la liste triée en JSON
        val jsonString = gson.toJson(sortedAppsList)

        // Stocker la liste dans DataStore
        context.dataStore.edit { preferences ->
            preferences[APPS_LIST_KEY] = jsonString
        }
    }



    val programsFlow: Flow<List<BlockProgram>> = context.dataStore.data
        .map { preferences ->
            preferences[USER_PROGRAMS_KEY]?.let { jsonString ->
                gson.fromJson(jsonString, object : TypeToken<List<BlockProgram>>() {}.type)
            } ?: emptyList()
        }

    private suspend fun savePrograms(programs: List<BlockProgram>) {
        val jsonString = gson.toJson(programs)
        context.dataStore.edit { preferences ->
            preferences[USER_PROGRAMS_KEY] = jsonString
        }
    }

    suspend fun addProgram(program: BlockProgram) {
        val currentPrograms = programsFlow.first().toMutableList()
//        val currentPrograms = mutableListOf<BlockProgram>()
        currentPrograms.add(program)
        savePrograms(currentPrograms)
    }
}
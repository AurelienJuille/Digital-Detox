package com.example.digitaldetoxv3.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val blockProgramsRepository: BlockProgramsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val blockProgramsRepository: BlockProgramsRepository by lazy {
        OfflineBlockProgramsRepository(MyDatabase.getDatabase(context).blockProgramDao())
    }
}
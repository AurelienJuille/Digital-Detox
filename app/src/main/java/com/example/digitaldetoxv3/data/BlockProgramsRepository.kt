package com.example.digitaldetoxv3.data

import kotlinx.coroutines.flow.Flow

interface BlockProgramsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllProgramsStream(): Flow<List<BlockProgram>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getProgramStream(id: Int): Flow<BlockProgram?>

    /**
     * Insert item in the data source
     */
    suspend fun insertProgram(item: BlockProgram)

    /**
     * Delete item from the data source
     */
    suspend fun deleteProgram(item: BlockProgram)

    /**
     * Update item in the data source
     */
    suspend fun updateProgram(item: BlockProgram)
}
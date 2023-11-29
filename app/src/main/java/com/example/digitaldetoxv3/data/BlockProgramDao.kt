package com.example.digitaldetoxv3.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BlockProgramDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(blockProgram: BlockProgram)

    @Update
    suspend fun update(blockProgram: BlockProgram)

    @Delete
    suspend fun delete(blockProgram: BlockProgram)

    @Query("SELECT * from BlockPrograms WHERE id = :id")
    fun getBlockProgram(id: Int): Flow<BlockProgram>

    @Query("SELECT * from BlockPrograms ORDER BY name ASC")
    fun getAllBlockPrograms(): Flow<List<BlockProgram>>
}
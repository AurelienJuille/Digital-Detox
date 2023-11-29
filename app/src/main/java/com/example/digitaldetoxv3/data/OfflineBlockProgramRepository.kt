package com.example.digitaldetoxv3.data

import kotlinx.coroutines.flow.Flow

class OfflineBlockProgramsRepository(private val blockProgramDao: BlockProgramDao) : BlockProgramsRepository {
    override fun getAllProgramsStream(): Flow<List<BlockProgram>> = blockProgramDao.getAllBlockPrograms()

    override fun getProgramStream(id: Int): Flow<BlockProgram?> = blockProgramDao.getBlockProgram(id)

    override suspend fun insertProgram(item: BlockProgram) = blockProgramDao.insert(item)

    override suspend fun deleteProgram(item: BlockProgram) = blockProgramDao.delete(item)

    override suspend fun updateProgram(item: BlockProgram) = blockProgramDao.update(item)
}
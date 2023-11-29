package com.example.digitaldetoxv3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BlockPrograms")
data class BlockProgram (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val packages: String,
    val startTime: String,
    var endTime: String
)
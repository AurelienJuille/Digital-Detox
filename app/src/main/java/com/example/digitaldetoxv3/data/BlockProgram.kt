package com.example.digitaldetoxv3.data

import java.time.LocalTime

data class BlockProgram (
    val id: Int = 0,
    val name: String,
    val packages: String,
    val startTime: LocalTime,
    var endTime: LocalTime
)
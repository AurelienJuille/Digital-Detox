package com.example.digitaldetoxv3.data

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class BlockProgram(
    @SerializedName("name") val name: String,
    @SerializedName("pakcages") val packages: List<String>,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String
)

package com.example.digitaldetoxv3.data

sealed interface BlockProgramEvent {
    object SaveProgram: BlockProgramEvent
    data class SetName(val name: String): BlockProgramEvent
    data class SetPackages(val packages: String): BlockProgramEvent
    data class SetStartTime(val startTime: String): BlockProgramEvent
    data class SetEndTime(val endTime: String): BlockProgramEvent
    object ShowForm: BlockProgramEvent
    object HideForm: BlockProgramEvent
}
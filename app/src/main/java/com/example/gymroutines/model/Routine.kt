package com.example.gymroutines.model

data class Routine(
    var userId: String ="",
    var title: String ="",
    var level: String = "",
    var isPublic: Boolean = false,
    var imageURL: String = "",
    var description: String ="",
    var durationMin : Int = 0,
    var popularity: Int = 0,
    var exercises: List<RoutineExercisePreview> = listOf(),
    var equipment: List<String> = listOf(),
)
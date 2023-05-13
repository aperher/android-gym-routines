package com.example.gymroutines.model

data class RoutineExercisePreview(
    val name: String = "",
    val equipment: String = "",
    val primaryMuscles: String = "",
    val secundaryMuscles: List<String> = listOf(),
    val series: List<Int> = listOf()
)

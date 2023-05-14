package com.example.gymroutines.model

data class RoutineExercisePreview(
    val name: String = "",
    val equipment: Equipment = Equipment.Barbell,
    val primaryMuscles: Muscles = Muscles.Abs,
    val secundaryMuscles: List<String> = listOf(),
    var series: List<Int> = listOf()
)

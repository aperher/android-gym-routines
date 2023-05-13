package com.example.gymroutines.model

data class Routine(var userId: String = "", val isPublic: Boolean = false, val name: String = "", val exercises: List<RoutineExercisePreview> = listOf(), val description: String = "")
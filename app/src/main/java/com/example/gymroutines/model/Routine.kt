package com.example.gymroutines.model


data class Routine(var userId: String, val isPublic: Boolean, val name: String, val exercises: List<Exercises>, val description: String) {
    data class Exercises(
        val name: String,
        val equipment: String,
        val primaryMuscles: String,
        val secondaryMuscles: List<String>,
        val series: List<Int>
    )
}
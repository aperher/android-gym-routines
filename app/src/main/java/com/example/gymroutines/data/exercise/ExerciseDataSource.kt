package com.example.gymroutines.data.exercise

import com.example.gymroutines.model.Exercise

interface ExerciseDataSource {
    suspend fun getExercises(muscle: String? = null, equipment: String? = null): Result<List<Exercise>>
}
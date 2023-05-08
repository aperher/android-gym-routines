package com.example.gymroutines.data.exercise

import com.example.gymroutines.model.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    suspend fun getExercises(muscle: String? = null, equipment: String? = null): Result<List<Exercise>>
}
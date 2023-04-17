package com.example.gymroutines.data.Exercice

import com.example.gymroutines.model.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun getExercises(muscle: String, equipment: String): Flow<List<Exercise>>

}
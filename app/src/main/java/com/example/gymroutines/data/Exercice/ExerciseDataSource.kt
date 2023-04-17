package com.example.gymroutines.data.Exercice

import com.example.gymroutines.model.Exercise
import com.example.gymroutines.model.Routine
import kotlinx.coroutines.flow.Flow

interface ExerciseDataSource {
    fun getExercises(muscle: String, equipment: String): Flow<List<Exercise>>
}
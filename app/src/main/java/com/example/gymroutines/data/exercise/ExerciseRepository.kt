package com.example.gymroutines.data.exercise

import com.example.gymroutines.model.Equipment
import com.example.gymroutines.model.Exercise
import com.example.gymroutines.model.Muscles
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    suspend fun getExercises(): Result<List<Exercise>>
}
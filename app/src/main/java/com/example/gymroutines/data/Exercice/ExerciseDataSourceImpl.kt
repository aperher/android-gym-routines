package com.example.gymroutines.data.Exercice

import com.example.gymroutines.model.Exercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class ExerciseDataSourceImpl: ExerciseDataSource {


    override fun getExercises(muscle: String, equipment: String): Flow<List<Exercise>> {
       return emptyFlow()
    }
}
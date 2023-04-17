package com.example.gymroutines.data.Exercice


import com.example.gymroutines.model.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(val datasource: ExerciseDataSource): ExerciseRepository {
    override fun getExercises (muscle: String, equipment: String): Flow<List<Exercise>> {
       return  datasource.getExercises(muscle, equipment)
    }
}
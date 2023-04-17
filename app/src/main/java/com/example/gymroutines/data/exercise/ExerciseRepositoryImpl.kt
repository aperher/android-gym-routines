package com.example.gymroutines.data.exercise


import com.example.gymroutines.model.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(val datasource: ExerciseDataSource): ExerciseRepository {
    override suspend fun getExercises (muscle: String?, equipment: String?): Result<List<Exercise>> {
       return datasource.getExercises(muscle, equipment)
    }
}
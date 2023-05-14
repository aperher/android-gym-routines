package com.example.gymroutines.data.exercise


import com.example.gymroutines.model.Equipment
import com.example.gymroutines.model.Exercise
import com.example.gymroutines.model.Muscles
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(val datasource: ExerciseDataSource): ExerciseRepository {
    override suspend fun getExercises (): Result<List<Exercise>> {
       return datasource.getExercises()
    }
}
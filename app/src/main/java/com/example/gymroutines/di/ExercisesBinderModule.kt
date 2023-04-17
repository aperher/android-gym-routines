package com.example.gymroutines.di

import com.example.gymroutines.data.exercise.ExerciseDataSource
import com.example.gymroutines.data.exercise.ExerciseDataSourceImpl
import com.example.gymroutines.data.exercise.ExerciseRepository
import com.example.gymroutines.data.exercise.ExerciseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ExercisesBinderModule {
    @Binds
    abstract fun bindExercisesRepository(exerciseRepository: ExerciseRepositoryImpl): ExerciseRepository

    @Binds
    abstract fun bindExercisesDataSource(exerciseDataSource: ExerciseDataSourceImpl): ExerciseDataSource
}
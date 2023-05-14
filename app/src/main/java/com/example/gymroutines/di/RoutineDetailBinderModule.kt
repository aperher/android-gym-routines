package com.example.gymroutines.di

import com.example.gymroutines.data.routineDetails.RoutineDetailDataSource
import com.example.gymroutines.data.routineDetails.RoutineDetailDataSourceImpl
import com.example.gymroutines.data.routineDetails.RoutineDetailRepository
import com.example.gymroutines.data.routineDetails.RoutineDetailRepositoryImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RoutineDetailBinderModule {
    @Binds
    abstract fun bindRoutinesDetailRepository(RoutineDetailRepository: RoutineDetailRepositoryImpl): RoutineDetailRepository

    @Binds
    abstract fun bindRoutinesDetailDataSource(RoutineDetailDataSource: RoutineDetailDataSourceImpl): RoutineDetailDataSource
}
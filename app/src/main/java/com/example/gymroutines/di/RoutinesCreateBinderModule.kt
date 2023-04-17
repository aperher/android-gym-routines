package com.example.gymroutines.di

import com.example.gymroutines.data.routinesCreate.RoutinesCreateDataSource
import com.example.gymroutines.data.routinesCreate.RoutinesCreateDataSourceImpl
import com.example.gymroutines.data.routinesCreate.RoutinesCreateRepository
import com.example.gymroutines.data.routinesCreate.RoutinesCreateRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RoutinesCreateBinderModule {
    @Binds
    abstract fun bindRoutinesCreateRepository(routinesCreateRepository: RoutinesCreateRepositoryImpl): RoutinesCreateRepository

    @Binds
    abstract fun bindRoutinesCreateDataSource(routinesCreateDataSource: RoutinesCreateDataSourceImpl): RoutinesCreateDataSource
}
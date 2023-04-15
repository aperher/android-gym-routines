package com.example.gymroutines.di

import com.example.gymroutines.data.routinesCatalog.RoutinesCatalogDataSource
import com.example.gymroutines.data.routinesCatalog.RoutinesCatalogDataSourceImpl
import com.example.gymroutines.data.routinesCatalog.RoutinesCatalogRepository
import com.example.gymroutines.data.routinesCatalog.RoutinesCatalogRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RoutinesCatalogBinderModule {
    @Binds
    abstract fun bindRoutinesCatalogRepository(routinesCatalogRepository: RoutinesCatalogRepositoryImpl): RoutinesCatalogRepository

    @Binds
    abstract fun bindRoutinesCatalogDataSource(routinesCatalogDataSource: RoutinesCatalogDataSourceImpl): RoutinesCatalogDataSource
}
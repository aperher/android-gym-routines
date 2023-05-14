package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.model.Catalog
import com.example.gymroutines.model.CatalogType
import com.example.gymroutines.model.FilterType
import com.example.gymroutines.model.RoutinePreview
import kotlinx.coroutines.flow.Flow

interface RoutinesCatalogRepository {
    fun getRoutinesCatalog(): Flow<List<Catalog>>
    suspend fun getRoutinesByCatalog(catalogTitle: CatalogType): Result<List<RoutinePreview>>
    suspend fun getFilteredRoutines(filters: Map<FilterType, MutableList<String>>): Result<List<RoutinePreview>>

    suspend fun searchRoutines(title: String): Result<List<RoutinePreview>>
}
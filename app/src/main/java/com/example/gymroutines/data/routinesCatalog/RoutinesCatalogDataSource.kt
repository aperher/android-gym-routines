package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.data.routinesCatalog.model.CatalogDto
import com.example.gymroutines.data.routinesCatalog.model.RoutinePreviewDto
import com.example.gymroutines.model.CatalogType
import com.example.gymroutines.model.FilterType
import kotlinx.coroutines.flow.Flow

interface RoutinesCatalogDataSource {
    fun getRoutinesCatalog(): Flow<List<CatalogDto>>
    suspend fun getRoutinesByCatalog(catalogTitle: CatalogType): Result<List<RoutinePreviewDto>>
    suspend fun getFilteredRoutines(filters: Map<FilterType, MutableList<String>>): Result<List<RoutinePreviewDto>>

    suspend fun searchRoutines(title: String): Result<List<RoutinePreviewDto>>
}
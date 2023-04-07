package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.data.routinesCatalog.model.CatalogDto
import kotlinx.coroutines.flow.Flow

interface RoutinesCatalogDataSource {
    fun getRoutinesCatalog(): Flow<List<CatalogDto>>
}
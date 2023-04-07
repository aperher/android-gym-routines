package com.example.gymroutines.data.routinesCatalog

import com.example.gymroutines.model.Catalog
import kotlinx.coroutines.flow.Flow

interface RoutinesCatalogRepository {
    fun getRoutinesCatalog(): Flow<List<Catalog>>
}
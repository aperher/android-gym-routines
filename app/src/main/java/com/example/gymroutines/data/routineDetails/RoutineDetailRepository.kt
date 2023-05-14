package com.example.gymroutines.data.routineDetails

import com.example.gymroutines.model.RoutineDetail
import kotlinx.coroutines.flow.Flow

interface RoutineDetailRepository {
    fun addFavourite(idRoutine: String)
    fun removeFavourite(idRoutine: String)
    fun deleteRoutine(idRoutine: String)
    fun getRoutine(idRoutine: String) : Flow<RoutineDetail>
}
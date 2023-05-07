package com.example.gymroutines.data.routinedatails

import com.example.gymroutines.model.RoutineDetail
import kotlinx.coroutines.flow.Flow

interface RoutineDetailRepository {
    fun deleteRoutine(idRoutine: String)
    fun getRoutine(idRoutine: String) : Flow<RoutineDetail>
}
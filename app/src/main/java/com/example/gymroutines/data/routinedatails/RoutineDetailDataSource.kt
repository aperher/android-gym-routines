package com.example.gymroutines.data.routinedatails

import com.example.gymroutines.model.RoutineDetail
import kotlinx.coroutines.flow.Flow

interface RoutineDetailDataSource {
    fun deleteRoutine(idRoutine: String)
    fun getRoutine(idRoutine: String) : Flow<RoutineDetail>
}
package com.example.gymroutines.data.routinedatails

import com.example.gymroutines.model.RoutineDetail

interface RoutineDetailRepository {
    fun deleteRoutine(idRoutine: String)
    fun getRoutine(idRoutine: String) : RoutineDetail
}
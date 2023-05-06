package com.example.gymroutines.data.routinedatails

import com.example.gymroutines.model.RoutineDetail

interface RoutineDetailDataSource {
    fun deleteRoutine(idRoutine: String)
    fun getRoutine(idRoutine: String) : RoutineDetail
}
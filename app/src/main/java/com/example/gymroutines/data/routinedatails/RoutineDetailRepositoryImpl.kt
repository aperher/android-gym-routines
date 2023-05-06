package com.example.gymroutines.data.routinedatails

import com.example.gymroutines.model.RoutineDetail
import javax.inject.Inject

class RoutineDetailRepositoryImpl @Inject constructor(private val dataSource: RoutineDetailDataSource): RoutineDetailRepository {
    override fun deleteRoutine(idRoutine: String) {
        dataSource.deleteRoutine(idRoutine)
    }
    fun getRoutine(idRoutine: String): RoutineDetail {
        return dataSource.getRoutine(idRoutine)
    }
}
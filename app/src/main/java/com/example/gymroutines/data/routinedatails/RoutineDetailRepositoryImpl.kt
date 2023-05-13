package com.example.gymroutines.data.routinedatails

import com.example.gymroutines.model.RoutineDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoutineDetailRepositoryImpl @Inject constructor(private val dataSource: RoutineDetailDataSource): RoutineDetailRepository {
    override fun addFavourite(idRoutine: String) {
        dataSource.addFavourite(idRoutine)
    }

    override fun removeFavourite(idRoutine: String) {
        dataSource.removeFavourite(idRoutine)
    }

    override fun deleteRoutine(idRoutine: String) {
        dataSource.deleteRoutine(idRoutine)
    }

    override fun getRoutine(idRoutine: String): Flow<RoutineDetail> {
        return dataSource.getRoutine(idRoutine)
    }
}
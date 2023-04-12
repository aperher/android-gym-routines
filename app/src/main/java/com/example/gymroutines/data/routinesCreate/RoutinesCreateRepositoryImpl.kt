package com.example.gymroutines.data.routinesCreate



import com.example.gymroutines.model.Routine
import javax.inject.Inject

class RoutinesCreateRepositoryImpl @Inject constructor(
    private val routinesCreateDataSource: RoutinesCreateDataSource
) : RoutinesCreateRepository {
    override suspend fun createRoutine(routine: Routine): Boolean {
        return routinesCreateDataSource.createRoutine(routine)
    }
}
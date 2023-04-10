package com.example.gymroutines.data.routinesCreate

import com.example.gymroutines.model.Routine


interface RoutinesCreateDataSource {
    suspend fun createRoutine(routine: Routine): Boolean
}
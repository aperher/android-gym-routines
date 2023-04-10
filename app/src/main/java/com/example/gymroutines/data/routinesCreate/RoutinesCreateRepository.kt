package com.example.gymroutines.data.routinesCreate

import com.example.gymroutines.model.Routine

interface RoutinesCreateRepository {
    suspend fun createRoutine(routine: Routine): Boolean
}